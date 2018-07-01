/*
 * Copyright 2018-2019 waiting1s, The Waiting1s Project
 */

package com.w1s.api.test.common;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:context.xml"})
@Transactional("transactionManager")
@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBase extends AbstractTransactionalJUnit4SpringContextTests {

}
