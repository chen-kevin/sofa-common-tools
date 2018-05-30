/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.common.log.factory;

import com.alipay.sofa.common.log.SpaceInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuanbei
 * @since 2017/07/03
 */
public class LoggerSpaceManagerOverrideUsageTest {

    @Test
    public void testLog4j1() throws IOException {
        LoggerSpaceFactory4LogbackBuilder builder = new LoggerSpaceFactory4LogbackBuilder(
            new SpaceInfo());
        ClassLoader classLoader = LoggerSpaceManagerOverrideUsageTest.class.getClassLoader();

        Assert.assertNull(builder.getResource(classLoader, null));

        List<URL> configFileUrls = new ArrayList<URL>();
        Assert.assertNull(builder.getResource(classLoader, configFileUrls));

        URL url1 = classLoader.getResource("com/alipay/sofa/testover1/log/log4j/log-conf.xml");
        configFileUrls.add(url1);
        Assert.assertEquals(url1, builder.getResource(classLoader, configFileUrls));

        URL url2 = classLoader.getResource("com/alipay/sofa/testover2/log/log4j/log-conf.xml");
        configFileUrls.add(url2);
        Assert.assertEquals(url2, builder.getResource(classLoader, configFileUrls));

        URL url3 = classLoader.getResource("com/alipay/sofa/testover3/log/log4j/log-conf.xml");
        configFileUrls.add(url3);
        Assert.assertEquals(url2, builder.getResource(classLoader, configFileUrls));
    }
}
