/* **************************************************************************
 * Copyright (C) 2010-2011 VMware, Inc. All rights reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0.
 * Please see the LICENSE file to review the full text of the Apache License 2.0.
 * You may not use this product except in compliance with the License.
 * ************************************************************************** */
package com.vmware.lmock;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.vmware.lmock.exception.IllegalClassDefinitionException;
import com.vmware.lmock.impl.Mock;
import com.vmware.lmock.impl.Scenario;
import com.vmware.lmock.impl.Stubs;

/**
 * Validation of the specification of <code>anyOf</code> and
 * <code>aNonNullOf</code>, denoted as "any" clauses.
 *
 * <p>
 * These tests mainly focus on the coherence of the definitions and the proper
 * registration of those clauses.
 * </p>
 */
public class AnyClauseSpecificationTest {
    /**
     * Validates the any clauses with primitive types.
     */
    @Test
    public void testAnyClauseWithPrimitiveTypes() {
        new Scenario() {
            {
                expect(Dalton.joe).setBoolean(anyOf(Boolean.class));
                String instance = expect().toString();
                assertTrue(instance.contains("setBoolean(Boolean=<ANY>)"));
                expect(Dalton.joe).setBoolean_(anyOf(Boolean.class));
                instance = expect().toString();
                assertTrue(instance.contains("setBoolean_(Boolean=<ANY>)"));

                expect(Dalton.joe).setBoolean(anyOf(boolean.class));
                instance = expect().toString();
                assertTrue(instance.contains("setBoolean(Boolean=<ANY>)"));
                expect(Dalton.joe).setBoolean_(anyOf(boolean.class));
                instance = expect().toString();
                assertTrue(instance.contains("setBoolean_(Boolean=<ANY>)"));

                expect(Dalton.joe).setBoolean(aNonNullOf(Boolean.class));
                instance = expect().toString();
                assertTrue(instance.contains("setBoolean(Boolean!=null)"));
                expect(Dalton.joe).setBoolean_(aNonNullOf(Boolean.class));
                instance = expect().toString();
                assertTrue(instance.contains("setBoolean_(Boolean!=null)"));

                expect(Dalton.joe).setBoolean(aNonNullOf(boolean.class));
                instance = expect().toString();
                assertTrue(instance.contains("setBoolean(Boolean!=null)"));
                expect(Dalton.joe).setBoolean_(aNonNullOf(boolean.class));
                instance = expect().toString();
                assertTrue(instance.contains("setBoolean_(Boolean!=null)"));

                expect(Dalton.joe).setChar(anyOf(Character.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar(Character=<ANY>)"));
                expect(Dalton.joe).setChar_(anyOf(Character.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar_(Character=<ANY>)"));

                expect(Dalton.joe).setChar(anyOf(char.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar(Character=<ANY>)"));
                expect(Dalton.joe).setChar_(anyOf(char.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar_(Character=<ANY>)"));

                expect(Dalton.joe).setChar(aNonNullOf(Character.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar(Character!=null)"));
                expect(Dalton.joe).setChar_(aNonNullOf(Character.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar_(Character!=null)"));

                expect(Dalton.joe).setChar(aNonNullOf(char.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar(Character!=null)"));
                expect(Dalton.joe).setChar_(aNonNullOf(char.class));
                instance = expect().toString();
                assertTrue(instance.contains("setChar_(Character!=null)"));

                expect(Dalton.joe).setByte(anyOf(Byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte(Byte=<ANY>)"));
                expect(Dalton.joe).setByte_(anyOf(Byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte_(Byte=<ANY>)"));

                expect(Dalton.joe).setByte(anyOf(byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte(Byte=<ANY>)"));
                expect(Dalton.joe).setByte_(anyOf(byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte_(Byte=<ANY>)"));

                expect(Dalton.joe).setByte(aNonNullOf(Byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte(Byte!=null)"));
                expect(Dalton.joe).setByte_(aNonNullOf(Byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte_(Byte!=null)"));

                expect(Dalton.joe).setByte(aNonNullOf(byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte(Byte!=null)"));
                expect(Dalton.joe).setByte_(aNonNullOf(byte.class));
                instance = expect().toString();
                assertTrue(instance.contains("setByte_(Byte!=null)"));

                expect(Dalton.joe).setShort(anyOf(Short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort(Short=<ANY>)"));
                expect(Dalton.joe).setShort_(anyOf(Short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort_(Short=<ANY>)"));

                expect(Dalton.joe).setShort(anyOf(short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort(Short=<ANY>)"));
                expect(Dalton.joe).setShort_(anyOf(short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort_(Short=<ANY>)"));

                expect(Dalton.joe).setShort(aNonNullOf(Short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort(Short!=null)"));
                expect(Dalton.joe).setShort_(aNonNullOf(Short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort_(Short!=null)"));

                expect(Dalton.joe).setShort(aNonNullOf(short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort(Short!=null)"));
                expect(Dalton.joe).setShort_(aNonNullOf(short.class));
                instance = expect().toString();
                assertTrue(instance.contains("setShort_(Short!=null)"));

                expect(Dalton.joe).setInt(anyOf(Integer.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt(Integer=<ANY>)"));
                expect(Dalton.joe).setInt_(anyOf(Integer.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt_(Integer=<ANY>)"));

                expect(Dalton.joe).setInt(anyOf(int.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt(Integer=<ANY>)"));
                expect(Dalton.joe).setInt_(anyOf(int.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt_(Integer=<ANY>)"));

                expect(Dalton.joe).setInt(aNonNullOf(Integer.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt(Integer!=null)"));
                expect(Dalton.joe).setInt_(aNonNullOf(Integer.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt_(Integer!=null)"));

                expect(Dalton.joe).setInt(aNonNullOf(int.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt(Integer!=null)"));
                expect(Dalton.joe).setInt_(aNonNullOf(int.class));
                instance = expect().toString();
                assertTrue(instance.contains("setInt_(Integer!=null)"));

                expect(Dalton.joe).setLong(anyOf(Long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong(Long=<ANY>)"));
                expect(Dalton.joe).setLong_(anyOf(Long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong_(Long=<ANY>)"));

                expect(Dalton.joe).setLong(anyOf(long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong(Long=<ANY>)"));
                expect(Dalton.joe).setLong_(anyOf(long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong_(Long=<ANY>)"));

                expect(Dalton.joe).setLong(aNonNullOf(Long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong(Long!=null)"));
                expect(Dalton.joe).setLong_(aNonNullOf(Long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong_(Long!=null)"));

                expect(Dalton.joe).setLong(aNonNullOf(long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong(Long!=null)"));
                expect(Dalton.joe).setLong_(aNonNullOf(long.class));
                instance = expect().toString();
                assertTrue(instance.contains("setLong_(Long!=null)"));

                expect(Dalton.joe).setFloat(anyOf(Float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat(Float=<ANY>)"));
                expect(Dalton.joe).setFloat_(anyOf(Float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat_(Float=<ANY>)"));

                expect(Dalton.joe).setFloat(anyOf(float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat(Float=<ANY>)"));
                expect(Dalton.joe).setFloat_(anyOf(float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat_(Float=<ANY>)"));

                expect(Dalton.joe).setFloat(aNonNullOf(Float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat(Float!=null)"));
                expect(Dalton.joe).setFloat_(aNonNullOf(Float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat_(Float!=null)"));

                expect(Dalton.joe).setFloat(aNonNullOf(float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat(Float!=null)"));
                expect(Dalton.joe).setFloat_(aNonNullOf(float.class));
                instance = expect().toString();
                assertTrue(instance.contains("setFloat_(Float!=null)"));

                expect(Dalton.joe).setDouble(anyOf(Double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble(Double=<ANY>)"));
                expect(Dalton.joe).setDouble_(anyOf(Double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble_(Double=<ANY>)"));

                expect(Dalton.joe).setDouble(anyOf(double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble(Double=<ANY>)"));
                expect(Dalton.joe).setDouble_(anyOf(double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble_(Double=<ANY>)"));

                expect(Dalton.joe).setDouble(aNonNullOf(Double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble(Double!=null)"));
                expect(Dalton.joe).setDouble_(aNonNullOf(Double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble_(Double!=null)"));

                expect(Dalton.joe).setDouble(aNonNullOf(double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble(Double!=null)"));
                expect(Dalton.joe).setDouble_(aNonNullOf(double.class));
                instance = expect().toString();
                assertTrue(instance.contains("setDouble_(Double!=null)"));
            }
        };
    }

    /**
     * Validates the any clauses with primitive types.
     */
    @Test
    public void testAnyClauseWithPrimitiveTypesForStubs() {
        new Stubs() {
            {
                stub(Dalton.joe).setBoolean(anyOf(Boolean.class));
                String instance = stub().toString();
                assertTrue(instance.contains("setBoolean(Boolean=<ANY>)"));
                stub(Dalton.joe).setBoolean_(anyOf(Boolean.class));
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean_(Boolean=<ANY>)"));

                stub(Dalton.joe).setBoolean(anyOf(boolean.class));
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean(Boolean=<ANY>)"));
                stub(Dalton.joe).setBoolean_(anyOf(boolean.class));
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean_(Boolean=<ANY>)"));

                stub(Dalton.joe).setBoolean(aNonNullOf(Boolean.class));
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean(Boolean!=null)"));
                stub(Dalton.joe).setBoolean_(aNonNullOf(Boolean.class));
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean_(Boolean!=null)"));

                stub(Dalton.joe).setBoolean(aNonNullOf(boolean.class));
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean(Boolean!=null)"));
                stub(Dalton.joe).setBoolean_(aNonNullOf(boolean.class));
                instance = stub().toString();
                assertTrue(instance.contains("setBoolean_(Boolean!=null)"));

                stub(Dalton.joe).setChar(anyOf(Character.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar(Character=<ANY>)"));
                stub(Dalton.joe).setChar_(anyOf(Character.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar_(Character=<ANY>)"));

                stub(Dalton.joe).setChar(anyOf(char.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar(Character=<ANY>)"));
                stub(Dalton.joe).setChar_(anyOf(char.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar_(Character=<ANY>)"));

                stub(Dalton.joe).setChar(aNonNullOf(Character.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar(Character!=null)"));
                stub(Dalton.joe).setChar_(aNonNullOf(Character.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar_(Character!=null)"));

                stub(Dalton.joe).setChar(aNonNullOf(char.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar(Character!=null)"));
                stub(Dalton.joe).setChar_(aNonNullOf(char.class));
                instance = stub().toString();
                assertTrue(instance.contains("setChar_(Character!=null)"));

                stub(Dalton.joe).setByte(anyOf(Byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte(Byte=<ANY>)"));
                stub(Dalton.joe).setByte_(anyOf(Byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte_(Byte=<ANY>)"));

                stub(Dalton.joe).setByte(anyOf(byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte(Byte=<ANY>)"));
                stub(Dalton.joe).setByte_(anyOf(byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte_(Byte=<ANY>)"));

                stub(Dalton.joe).setByte(aNonNullOf(Byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte(Byte!=null)"));
                stub(Dalton.joe).setByte_(aNonNullOf(Byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte_(Byte!=null)"));

                stub(Dalton.joe).setByte(aNonNullOf(byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte(Byte!=null)"));
                stub(Dalton.joe).setByte_(aNonNullOf(byte.class));
                instance = stub().toString();
                assertTrue(instance.contains("setByte_(Byte!=null)"));

                stub(Dalton.joe).setShort(anyOf(Short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort(Short=<ANY>)"));
                stub(Dalton.joe).setShort_(anyOf(Short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort_(Short=<ANY>)"));

                stub(Dalton.joe).setShort(anyOf(short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort(Short=<ANY>)"));
                stub(Dalton.joe).setShort_(anyOf(short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort_(Short=<ANY>)"));

                stub(Dalton.joe).setShort(aNonNullOf(Short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort(Short!=null)"));
                stub(Dalton.joe).setShort_(aNonNullOf(Short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort_(Short!=null)"));

                stub(Dalton.joe).setShort(aNonNullOf(short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort(Short!=null)"));
                stub(Dalton.joe).setShort_(aNonNullOf(short.class));
                instance = stub().toString();
                assertTrue(instance.contains("setShort_(Short!=null)"));

                stub(Dalton.joe).setInt(anyOf(Integer.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt(Integer=<ANY>)"));
                stub(Dalton.joe).setInt_(anyOf(Integer.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt_(Integer=<ANY>)"));

                stub(Dalton.joe).setInt(anyOf(int.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt(Integer=<ANY>)"));
                stub(Dalton.joe).setInt_(anyOf(int.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt_(Integer=<ANY>)"));

                stub(Dalton.joe).setInt(aNonNullOf(Integer.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt(Integer!=null)"));
                stub(Dalton.joe).setInt_(aNonNullOf(Integer.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt_(Integer!=null)"));

                stub(Dalton.joe).setInt(aNonNullOf(int.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt(Integer!=null)"));
                stub(Dalton.joe).setInt_(aNonNullOf(int.class));
                instance = stub().toString();
                assertTrue(instance.contains("setInt_(Integer!=null)"));

                stub(Dalton.joe).setLong(anyOf(Long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong(Long=<ANY>)"));
                stub(Dalton.joe).setLong_(anyOf(Long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong_(Long=<ANY>)"));

                stub(Dalton.joe).setLong(anyOf(long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong(Long=<ANY>)"));
                stub(Dalton.joe).setLong_(anyOf(long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong_(Long=<ANY>)"));

                stub(Dalton.joe).setLong(aNonNullOf(Long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong(Long!=null)"));
                stub(Dalton.joe).setLong_(aNonNullOf(Long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong_(Long!=null)"));

                stub(Dalton.joe).setLong(aNonNullOf(long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong(Long!=null)"));
                stub(Dalton.joe).setLong_(aNonNullOf(long.class));
                instance = stub().toString();
                assertTrue(instance.contains("setLong_(Long!=null)"));

                stub(Dalton.joe).setFloat(anyOf(Float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat(Float=<ANY>)"));
                stub(Dalton.joe).setFloat_(anyOf(Float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat_(Float=<ANY>)"));

                stub(Dalton.joe).setFloat(anyOf(float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat(Float=<ANY>)"));
                stub(Dalton.joe).setFloat_(anyOf(float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat_(Float=<ANY>)"));

                stub(Dalton.joe).setFloat(aNonNullOf(Float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat(Float!=null)"));
                stub(Dalton.joe).setFloat_(aNonNullOf(Float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat_(Float!=null)"));

                stub(Dalton.joe).setFloat(aNonNullOf(float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat(Float!=null)"));
                stub(Dalton.joe).setFloat_(aNonNullOf(float.class));
                instance = stub().toString();
                assertTrue(instance.contains("setFloat_(Float!=null)"));

                stub(Dalton.joe).setDouble(anyOf(Double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble(Double=<ANY>)"));
                stub(Dalton.joe).setDouble_(anyOf(Double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble_(Double=<ANY>)"));

                stub(Dalton.joe).setDouble(anyOf(double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble(Double=<ANY>)"));
                stub(Dalton.joe).setDouble_(anyOf(double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble_(Double=<ANY>)"));

                stub(Dalton.joe).setDouble(aNonNullOf(Double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble(Double!=null)"));
                stub(Dalton.joe).setDouble_(aNonNullOf(Double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble_(Double!=null)"));

                stub(Dalton.joe).setDouble(aNonNullOf(double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble(Double!=null)"));
                stub(Dalton.joe).setDouble_(aNonNullOf(double.class));
                instance = stub().toString();
                assertTrue(instance.contains("setDouble_(Double!=null)"));
            }
        };
    }

    /**
     * Validates the registration of a basic class in an any clause.
     */
    @Test
    public void testAnyClauseWithObject() {
        new Scenario() {
            {
                expect(Dalton.joe).setObject(anyOf(Object.class));
                String instance = expect().toString();
                assertTrue(instance.contains("setObject(Object=<ANY>)"));
                expect(Dalton.joe).setObject(aNonNullOf(Object.class));
                instance = expect().toString();
                assertTrue(instance.contains("setObject(Object!=null)"));
            }
        };
    }

    /**
     * Validates the registration of a basic class in an any clause.
     */
    @Test
    public void testAnyClauseWithObjectForStubs() {
        new Stubs() {
            {
                stub(Dalton.joe).setObject(anyOf(Object.class));
                String instance = stub().toString();
                assertTrue(instance.contains("setObject(Object=<ANY>)"));
                stub(Dalton.joe).setObject(aNonNullOf(Object.class));
                instance = stub().toString();
                assertTrue(instance.contains("setObject(Object!=null)"));
            }
        };
    }

    /**
     * Validates the registration of an inherited class in an any clause.
     */
    @Test
    public void testAnyClauseWithInheritedClass() {
        new Scenario() {
            {
                expect(Dalton.joe).setObject(anyOf(String.class));
                String instance = expect().toString();
                assertTrue(instance.contains("setObject(String=<ANY>)"));
                expect(Dalton.joe).setObject(aNonNullOf(File.class));
                instance = expect().toString();
                assertTrue(instance.contains("setObject(File!=null)"));
                // Interfaces should also work...
                expect(Dalton.joe).setObject(anyOf(Dalton.class));
                instance = expect().toString();
                assertTrue(instance.contains("setObject(Dalton=<ANY>)"));
                // And arrays should work too.
                expect(Dalton.joe).setObject(aNonNullOf(String[].class));
                instance = expect().toString();
                assertTrue(instance.contains("setObject(String[]!=null"));
            }
        };
    }

    /**
     * Validates the registration of an inherited class in an any clause.
     */
    @Test
    public void testAnyClauseWithInheritedClassForStubs() {
        new Stubs() {
            {
                stub(Dalton.joe).setObject(anyOf(String.class));
                String instance = stub().toString();
                assertTrue(instance.contains("setObject(String=<ANY>)"));
                stub(Dalton.joe).setObject(aNonNullOf(File.class));
                instance = stub().toString();
                assertTrue(instance.contains("setObject(File!=null)"));
                // Interfaces should also work...
                stub(Dalton.joe).setObject(anyOf(Dalton.class));
                instance = stub().toString();
                assertTrue(instance.contains("setObject(Dalton=<ANY>)"));
                // And arrays should work too.
                stub(Dalton.joe).setObject(aNonNullOf(String[].class));
                instance = stub().toString();
                assertTrue(instance.contains("setObject(String[]!=null"));
            }
        };
    }

    /**
     * Verifies that users can't pass a null argument to an any clause.
     *
     * <p>
     * This corner case may happen under very specific conditions.
     * </p>
     */
    @Test
    public void testAnyOfClauseWithNullArgument() {
        new Scenario() {
            {
                try {
                    expect(Dalton.joe).fillPocket(anyOf(null));
                    fail("defined an anyOf clause with a null argument");
                } catch (IllegalClassDefinitionException e) {
                }
            }
        };
    }

    /**
     * Verifies that users can't pass a null argument to an any clause.
     *
     * <p>
     * This corner case may happen under very specific conditions.
     * </p>
     */
    @Test
    public void testAnyOfClauseWithNullArgumentForStubs() {
        new Stubs() {
            {
                try {
                    stub(Dalton.joe).fillPocket(anyOf(null));
                    fail("defined an anyOf clause with a null argument");
                } catch (IllegalClassDefinitionException e) {
                }
            }
        };
    }

    /**
     * Verifies that users can't pass a null argument to an aNonNullOf clause.
     *
     * <p>
     * This corner case may happen under very specific conditions.
     * </p>
     */
    @Test
    public void testANonNullOfClauseWithNullArgument() {
        new Scenario() {
            {
                try {
                    expect(Dalton.joe).fillPocket(aNonNullOf(null));
                    fail("defined an aNonNullOf clause with a null argument");
                } catch (IllegalClassDefinitionException e) {
                }
            }
        };
    }

    /**
     * Verifies that users can't pass a null argument to an aNonNullOf clause.
     *
     * <p>
     * This corner case may happen under very specific conditions.
     * </p>
     */
    @Test
    public void testANonNullOfClauseWithNullArgumentForStubs() {
        new Stubs() {
            {
                try {
                    stub(Dalton.joe).fillPocket(aNonNullOf(null));
                    fail("defined an aNonNullOf clause with a null argument");
                } catch (IllegalClassDefinitionException e) {
                }
            }
        };
    }

    /**
     * A simple test to verify that the mix of with clauses works fine.
     */
    @Test
    public void testOfMixedWithClauses() {
        new Scenario() {
            {
                expect(Dalton.joe).fillPocket(with("123"), with(77.88),
                  anyOf(String.class), aNonNullOf(Mock.class));
                String instance = expect().toString();
                assertTrue(instance.contains("fillPocket(Object[]={String=123,Double=77.88,String=<ANY>,Mock!=null})"));
            }
        };
    }

    /**
     * A simple test to verify that the mix of with clauses works fine.
     */
    @Test
    public void testOfMixedWithClausesForStubs() {
        new Stubs() {
            {
                stub(Dalton.joe).fillPocket(with("123"), with(77.88),
                  anyOf(String.class), aNonNullOf(Mock.class));
                String instance = stub().toString();
                assertTrue(instance.contains("fillPocket(Object[]={String=123,Double=77.88,String=<ANY>,Mock!=null})"));
            }
        };
    }

    /**
     * Corner case due to implicit casts: expect something incompatible with the
     * method.
     */
    @Test
    public void testAnyOfClauseWithIncompatibleClass() {
        new Scenario() {
            {
                expect(Dalton.joe).setShort(anyOf(byte.class));
                String instance = expect().toString();
                assertTrue(instance.contains("setShort(Byte=<ANY>"));
            }
        };
    }

    /**
     * Corner case due to implicit casts: expect something incompatible with the
     * method.
     */
    @Test
    public void testAnyOfClauseWithIncompatibleClassForStubs() {
        new Stubs() {
            {
                stub(Dalton.joe).setShort(anyOf(byte.class));
                String instance = stub().toString();
                assertTrue(instance.contains("setShort(Byte=<ANY>"));
            }
        };
    }

    /**
     * Corner case due to implicit casts: expect something incompatible with the
     * method.
     */
    @Test
    public void testANonNullOfClauseWithIncompatibleClass() {
        new Scenario() {
            {
                expect(Dalton.joe).setShort(aNonNullOf(byte.class));
                String instance = expect().toString();
                assertTrue(instance.contains("setShort(Byte!=null)"));
            }
        };
    }

    /**
     * Corner case due to implicit casts: expect something incompatible with the
     * method.
     */
    @Test
    public void testANonNullOfClauseWithIncompatibleClassForStubs() {
        new Stubs() {
            {
                stub(Dalton.joe).setShort(aNonNullOf(byte.class));
                String instance = stub().toString();
                assertTrue(instance.contains("setShort(Byte!=null)"));
            }
        };
    }
}
