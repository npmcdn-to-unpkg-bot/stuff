/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mcstuff.javafx.spring;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;

/**
 * @author Thomas Darimont
 */
public abstract class AbstractJavaFxApplicationSupport extends Application {

	private static final Logger logger = LoggerFactory.getLogger(AbstractJavaFxApplicationSupport.class);
	protected static String[] savedArgs;

	protected static void launchApp(final Class<? extends AbstractJavaFxApplicationSupport> appClass,
			final String[] args) {
		AbstractJavaFxApplicationSupport.savedArgs = args;
		Application.launch(appClass, args);
	}

	private static boolean lockInstance(final String lockFile) {
		try {
			final File file = new File(lockFile);
			final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			final FileLock fileLock = randomAccessFile.getChannel().tryLock();
			if (fileLock != null) {
				Runtime.getRuntime().addShutdownHook(new Thread() {
					@Override
					public void run() {
						try {
							fileLock.release();
							randomAccessFile.close();
							file.delete();
						} catch (final Exception e) {
							logger.error("Unable to remove lock file: " + lockFile, e);
						}
					}
				});
				return true;
			}
		} catch (final Exception e) {
			logger.error("Unable to create and/or lock file: " + lockFile, e);
		}
		return false;
	}

	protected ConfigurableApplicationContext applicationContext;

	@Override
	public void init() throws Exception {
		if (!lockInstance("McStuff")) {
			logger.error("Another instance already running, shutting down");
			Platform.exit();
			return;
		}
		applicationContext = SpringApplication.run(getClass(), savedArgs);
		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		applicationContext.close();
	}

}
