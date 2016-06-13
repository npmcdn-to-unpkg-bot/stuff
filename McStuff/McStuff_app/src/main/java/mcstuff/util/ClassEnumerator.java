/* Copyright (C) 2016+ Michael Cassidy - All Rights Reserved
	This file is a part of McStuff

    McStuff is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    McStuff is distributed as sample code in the hope that it will be 
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with McStuff.  If not, see <http://www.gnu.org/licenses/>. 
 */
package mcstuff.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassEnumerator {
	private static final Logger logger = LoggerFactory.getLogger(ClassEnumerator.class);

	/**
	 * Give a package this method returns all classes contained in that package
	 * @param pkg
	 * @throws Exception 
	 */
	public static List<Class<?>> getClassesForPackage(final String pkgName) throws Exception {
		final ArrayList<Class<?>> classes = new ArrayList<>();

		// Get name of package and turn it to a relative path
		final String relPath = pkgName.replace('.', '/');

		// Get a File object for the package
		final Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources(relPath);

		// If we can't find the resource we throw an exception
		if (resources == null) {
			throw new RuntimeException("Unexpected problem: No resource for " + relPath);
		}

		while(resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			logger.info("Package: '" + pkgName + "' becomes Resource: '" + resource.toString() + "'");

			// If the resource is a jar get all classes from jar
			if (resource.toString().startsWith("jar:")) {
				classes.addAll(processJarfile(resource, pkgName));
			} else {
				classes.addAll(processDirectory(new File(resource.getPath()), pkgName));
			}
		}
		return classes;
	}

	private static Class<?> loadClass(final String className) {
		try {
			return Class.forName(className);
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
		}
	}

	/**
	 * Given a package name and a directory returns all classes within that directory
	 * @param directory
	 * @param pkgname
	 * @return Classes within Directory with package name
	 */
	public static List<Class<?>> processDirectory(final File directory, final String pkgname) {

		final ArrayList<Class<?>> classes = new ArrayList<>();

		logger.info("Reading Directory '" + directory + "'");

		// Get the list of the files contained in the package
		final String[] files = directory.list();
		for (final String fileName : files) {
			String className = null;

			// we are only interested in .class files
			if (fileName.endsWith(".class")) {
				// removes the .class extension
				className = pkgname + '.' + fileName.substring(0, fileName.length() - 6);
			}

			logger.info("FileName '" + fileName + "'  =>  class '" + className + "'");

			if (className != null) {
				classes.add(loadClass(className));
			}

			// If the file is a directory recursively class this method.
			final File subdir = new File(directory, fileName);
			if (subdir.isDirectory()) {
				classes.addAll(processDirectory(subdir, pkgname + '.' + fileName));
			}
		}
		return classes;
	}

	/**
	 * Given a jar file's URL and a package name returns all classes within jar file.
	 * @param resource
	 * @param pkgname
	 */
	public static List<Class<?>> processJarfile(final URL resource, final String pkgname) {
		final List<Class<?>> classes = new ArrayList<>();

		// Turn package name to relative path to jar file
		final String relPath = pkgname.replace('.', '/');
		final String resPath = resource.getPath();
		final String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
		logger.info("Reading JAR file: '" + jarPath + "'");

		try (JarFile jarFile = new JarFile(jarPath)) {
			// get contents of jar file and iterate through them
			final Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				final JarEntry entry = entries.nextElement();

				// Get content name from jar file
				final String entryName = entry.getName();
				String className = null;

				// If content is a class save class name.
				if (entryName.endsWith(".class") && entryName.startsWith(relPath)
						&& entryName.length() > relPath.length() + "/".length()) {
					className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
				}

				logger.info("JarEntry '" + entryName + "'  =>  class '" + className + "'");

				// If content is a class add class to List
				if (className != null) {
					classes.add(loadClass(className));
				}
			}
		} catch (final IOException e) {
			throw new RuntimeException("Unexpected IOException reading JAR File '" + jarPath + "'", e);
		}

		return classes;
	}
}