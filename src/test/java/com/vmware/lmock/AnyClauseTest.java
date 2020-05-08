/* **************************************************************************
 * Copyright (C) 2010-2011 VMware, Inc. All rights reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0.
 * Please see the LICENSE file to review the full text of the Apache License 2.0.
 * You may not use this product except in compliance with the License.
 * ************************************************************************** */
package com.vmware.lmock;

import org.junit.Test;
import static org.junit.Assert.*;

import com.vmware.lmock.checker.Occurrences;
import com.vmware.lmock.exception.ExpectationError;
import com.vmware.lmock.exception.UnexpectedInvocationError;
import com.vmware.lmock.exception.UnsatisfiedOccurrenceError;
import com.vmware.lmock.impl.Scenario;
import com.vmware.lmock.impl.Story;
import com.vmware.lmock.masquerade.Schemer;

/**
 * Tests of stories using scenarios based on expectations with 'with', 'anyOf'
 * and 'aNonNullOf' clauses.
 */
public class AnyClauseTest {
    /**
     * Validates expectations with anyOf clause using primitive arguments.
     */
    @Test
    public void testAnyOfWithPrimitiveTypes() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setBoolean(anyOf(boolean.class));
                expect(Dalton.joe).setBoolean_(anyOf(boolean.class));

                expect(Dalton.joe).setChar(anyOf(char.class));
                expect(Dalton.joe).setChar_(anyOf(char.class));

                expect(Dalton.joe).setByte(anyOf(byte.class));
                expect(Dalton.joe).setByte_(anyOf(byte.class));

                expect(Dalton.joe).setShort(anyOf(short.class));
                expect(Dalton.joe).setShort_(anyOf(short.class));

                expect(Dalton.joe).setInt(anyOf(int.class));
                expect(Dalton.joe).setInt_(anyOf(int.class));

                expect(Dalton.joe).setLong(anyOf(long.class));
                expect(Dalton.joe).setLong_(anyOf(long.class));

                expect(Dalton.joe).setFloat(anyOf(float.class));
                expect(Dalton.joe).setFloat_(anyOf(float.class));

                expect(Dalton.joe).setDouble(anyOf(double.class));
                expect(Dalton.joe).setDouble_(anyOf(double.class));
            }
        });
        story.begin();
        Dalton.joe.setBoolean(true);
        Dalton.joe.setBoolean(false);
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setBoolean_(false);
        Dalton.joe.setBoolean_(null);

        Dalton.joe.setChar('@');
        Dalton.joe.setChar_('_');
        Dalton.joe.setChar_(null);

        Dalton.joe.setByte((byte) 11);
        Dalton.joe.setByte_((byte) 49);
        Dalton.joe.setByte_(null);

        Dalton.joe.setShort((short) 124);
        Dalton.joe.setShort_((short) 233);
        Dalton.joe.setShort_(null);

        Dalton.joe.setInt(6553);
        Dalton.joe.setInt_(1234);
        Dalton.joe.setInt_(null);

        Dalton.joe.setLong(999L);
        Dalton.joe.setLong_(-167L);
        Dalton.joe.setLong_(null);

        Dalton.joe.setFloat((float) 3.141592);
        Dalton.joe.setFloat_((float) 154.155);
        Dalton.joe.setFloat_(null);
        story.end();
    }

    /**
     * Validates expectations with anyOf clause using primitive arguments.
     */
    @Test
    public void testAnyOfWithPrimitiveTypesMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean(true);
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean(false);
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean_(true);
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean_(false);
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean_(null);
        Dalton.joe.setBoolean(true);
        Dalton.joe.setBoolean(false);
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setBoolean_(false);
        Dalton.joe.setBoolean_(null);

        Schemer.willInvoke(1).of(Dalton.joe).setChar('@');
        Schemer.willInvoke(1).of(Dalton.joe).setChar_('_');
        Schemer.willInvoke(1).of(Dalton.joe).setChar_(null);
        Dalton.joe.setChar('@');
        Dalton.joe.setChar_('_');
        Dalton.joe.setChar_(null);

        Schemer.willInvoke(1).of(Dalton.joe).setByte((byte) 11);
        Schemer.willInvoke(1).of(Dalton.joe).setByte_((byte) 49);
        Schemer.willInvoke(1).of(Dalton.joe).setByte_(null);
        Dalton.joe.setByte((byte) 11);
        Dalton.joe.setByte_((byte) 49);
        Dalton.joe.setByte_(null);

        Schemer.willInvoke(1).of(Dalton.joe).setShort((short) 124);
        Schemer.willInvoke(1).of(Dalton.joe).setShort_((short) 233);
        Schemer.willInvoke(1).of(Dalton.joe).setShort_(null);
        Dalton.joe.setShort((short) 124);
        Dalton.joe.setShort_((short) 233);
        Dalton.joe.setShort_(null);

        Schemer.willInvoke(1).of(Dalton.joe).setInt(6553);
        Schemer.willInvoke(1).of(Dalton.joe).setInt_(1234);
        Schemer.willInvoke(1).of(Dalton.joe).setInt_(null);
        Dalton.joe.setInt(6553);
        Dalton.joe.setInt_(1234);
        Dalton.joe.setInt_(null);

        Schemer.willInvoke(1).of(Dalton.joe).setLong(999L);
        Schemer.willInvoke(1).of(Dalton.joe).setLong_(-167L);
        Schemer.willInvoke(1).of(Dalton.joe).setLong_(null);
        Dalton.joe.setLong(999L);
        Dalton.joe.setLong_(-167L);
        Dalton.joe.setLong_(null);

        Schemer.willInvoke(1).of(Dalton.joe).setFloat((float) 3.141592);
        Schemer.willInvoke(1).of(Dalton.joe).setFloat_((float) 154.155);
        Schemer.willInvoke(1).of(Dalton.joe).setFloat_(null);
        Dalton.joe.setFloat((float) 3.141592);
        Dalton.joe.setFloat_((float) 154.155);
        Dalton.joe.setFloat_(null);
        Schemer.end();
    }

    /**
     * Validates expectations with aNonNullOf clause using primitive arguments.
     */
    @Test
    public void testANonNullOfWithPrimitiveTypes() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setBoolean(aNonNullOf(boolean.class));
                expect(Dalton.joe).setBoolean_(aNonNullOf(boolean.class));

                expect(Dalton.joe).setChar(aNonNullOf(char.class));
                expect(Dalton.joe).setChar_(aNonNullOf(char.class));

                expect(Dalton.joe).setByte(aNonNullOf(byte.class));
                expect(Dalton.joe).setByte_(aNonNullOf(byte.class));

                expect(Dalton.joe).setShort(aNonNullOf(short.class));
                expect(Dalton.joe).setShort_(aNonNullOf(short.class));

                expect(Dalton.joe).setInt(aNonNullOf(int.class));
                expect(Dalton.joe).setInt_(aNonNullOf(int.class));

                expect(Dalton.joe).setLong(aNonNullOf(long.class));
                expect(Dalton.joe).setLong_(aNonNullOf(long.class));

                expect(Dalton.joe).setFloat(aNonNullOf(float.class));
                expect(Dalton.joe).setFloat_(aNonNullOf(float.class));

                expect(Dalton.joe).setDouble(aNonNullOf(double.class));
                expect(Dalton.joe).setDouble_(aNonNullOf(double.class));
            }
        });
        story.begin();
        Dalton.joe.setBoolean(true);
        Dalton.joe.setBoolean(false);
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setBoolean_(false);

        Dalton.joe.setChar('@');
        Dalton.joe.setChar_('_');

        Dalton.joe.setByte((byte) 11);
        Dalton.joe.setByte_((byte) 49);

        Dalton.joe.setShort((short) 124);
        Dalton.joe.setShort_((short) 233);

        Dalton.joe.setInt(6553);
        Dalton.joe.setInt_(1234);

        Dalton.joe.setLong(999L);
        Dalton.joe.setLong_(-167L);

        Dalton.joe.setFloat((float) 3.141592);
        Dalton.joe.setFloat_((float) 154.155);
        story.end();
    }

    /**
     * Validates expectations with aNonNullOf clause using primitive arguments.
     */
    @Test
    public void testANonNullOfWithPrimitiveTypesMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean(Schemer.aNonNullOf(
          boolean.class));
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean(Schemer.aNonNullOf(
          boolean.class));
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean_(Schemer.aNonNullOf(
          boolean.class));
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean_(Schemer.aNonNullOf(
          boolean.class));
        Dalton.joe.setBoolean(true);
        Dalton.joe.setBoolean(false);
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setBoolean_(false);

        Schemer.willInvoke(1).of(Dalton.joe).setChar(Schemer.aNonNullOf(char.class));
        Schemer.willInvoke(1).of(Dalton.joe).setChar_(Schemer.aNonNullOf(char.class));
        Dalton.joe.setChar('@');
        Dalton.joe.setChar_('_');

        Schemer.willInvoke(1).of(Dalton.joe).setByte(Schemer.aNonNullOf(byte.class));
        Schemer.willInvoke(1).of(Dalton.joe).setByte_(Schemer.aNonNullOf(byte.class));
        Dalton.joe.setByte((byte) 11);
        Dalton.joe.setByte_((byte) 49);

        Schemer.willInvoke(1).of(Dalton.joe).setShort(Schemer.aNonNullOf(short.class));
        Schemer.willInvoke(1).of(Dalton.joe).setShort_(Schemer.aNonNullOf(short.class));
        Dalton.joe.setShort((short) 124);
        Dalton.joe.setShort_((short) 233);

        Schemer.willInvoke(1).of(Dalton.joe).setInt(Schemer.aNonNullOf(int.class));
        Schemer.willInvoke(1).of(Dalton.joe).setInt_(Schemer.aNonNullOf(int.class));
        Dalton.joe.setInt(6553);
        Dalton.joe.setInt_(1234);

        Schemer.willInvoke(1).of(Dalton.joe).setLong(Schemer.aNonNullOf(long.class));
        Schemer.willInvoke(1).of(Dalton.joe).setLong_(Schemer.aNonNullOf(long.class));
        Dalton.joe.setLong(999L);
        Dalton.joe.setLong_(-167L);

        Schemer.willInvoke(1).of(Dalton.joe).setFloat(Schemer.aNonNullOf(float.class));
        Schemer.willInvoke(1).of(Dalton.joe).setFloat_(Schemer.aNonNullOf(float.class));
        Dalton.joe.setFloat((float) 3.141592);
        Dalton.joe.setFloat_((float) 154.155);
        Schemer.end();
    }

    /**
     * Verifies that aNonNullOf rejects null primitive values.
     */
    @Test
    public void testANonNullOfWithNullPrimitiveTypes() {
        // The last error reported
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setBoolean_(aNonNullOf(boolean.class));
                expect(Dalton.joe).setChar_(aNonNullOf(char.class));
                expect(Dalton.joe).setByte_(aNonNullOf(byte.class));
                expect(Dalton.joe).setShort_(aNonNullOf(short.class));
                expect(Dalton.joe).setInt_(aNonNullOf(int.class));
                expect(Dalton.joe).setLong_(aNonNullOf(long.class));
                expect(Dalton.joe).setFloat_(aNonNullOf(float.class));
                expect(Dalton.joe).setDouble_(aNonNullOf(double.class));
            }
        });

        story.begin();
        try {
            Dalton.joe.setBoolean_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        Dalton.joe.setBoolean_(true);
        try {
            Dalton.joe.setChar_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setChar_('W');
        try {
            Dalton.joe.setByte_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setChar_('W');
        Dalton.joe.setByte_((byte) 154);
        try {
            Dalton.joe.setShort_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setChar_('W');
        Dalton.joe.setByte_((byte) 154);
        Dalton.joe.setShort_((short) 154);
        try {
            Dalton.joe.setInt_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setChar_('W');
        Dalton.joe.setByte_((byte) 154);
        Dalton.joe.setShort_((short) 154);
        Dalton.joe.setInt_(154);
        try {
            Dalton.joe.setLong_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setChar_('W');
        Dalton.joe.setByte_((byte) 154);
        Dalton.joe.setShort_((short) 154);
        Dalton.joe.setInt_(154);
        Dalton.joe.setLong_((long) 154);
        try {
            Dalton.joe.setFloat_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        Dalton.joe.setBoolean_(true);
        Dalton.joe.setChar_('W');
        Dalton.joe.setByte_((byte) 154);
        Dalton.joe.setShort_((short) 154);
        Dalton.joe.setInt_(154);
        Dalton.joe.setLong_((long) 154);
        Dalton.joe.setFloat_((float) 154.0);
        try {
            Dalton.joe.setDouble_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that aNonNullOf rejects null primitive values.
     */
    @Test
    public void testANonNullOfWithNullPrimitiveTypesMA() {
        // The last error reported by the mock system.
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean(Schemer.aNonNullOf(
          boolean.class));
        try {
            Dalton.joe.setBoolean_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setChar_(Schemer.aNonNullOf(char.class));
        try {
            Dalton.joe.setChar_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setByte_(Schemer.aNonNullOf(byte.class));
        try {
            Dalton.joe.setByte_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setShort_(Schemer.aNonNullOf(short.class));
        try {
            Dalton.joe.setShort_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setInt_(Schemer.aNonNullOf(int.class));
        try {
            Dalton.joe.setInt_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setLong_(Schemer.aNonNullOf(long.class));
        try {
            Dalton.joe.setLong_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setFloat_(Schemer.aNonNullOf(float.class));
        try {
            Dalton.joe.setFloat_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setDouble_(
          Schemer.aNonNullOf(double.class));
        try {
            Dalton.joe.setDouble_(null);
            fail("set null to aNonNullOf clause");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates the behavior of anyOf with a "simple" class.
     */
    @Test
    public void testAnyOfWithSimpleClass() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setObject(anyOf(Object.class));
            }
        });
        story.begin();
        Dalton.joe.setObject("hello world");
        story.end();
    }

    /**
     * Validates the behavior of anyOf with a "simple" class.
     */
    @Test
    public void testAnyOfWithSimpleClassMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setObject(Schemer.anyOf(Object.class));
        Dalton.joe.setObject("hello world");
        Schemer.end();
    }

    /**
     * Verifies that aNonNullOf rejects null arguments.
     */
    @Test
    public void testANonNullOfWithAnInheritedClass() {
        // The last error reported by the test
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setObject(aNonNullOf(String.class));
            }
        });

        story.begin();
        Dalton.joe.setObject("boo!");
        try {
            Dalton.joe.setObject(null);
            fail("passed a null argument with aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that aNonNullOf rejects null arguments.
     */
    @Test
    public void testANonNullOfWithAnInheritedClassMA() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(Occurrences.atLeast(1)).of(Dalton.joe).setObject(Schemer.aNonNullOf(String.class));
        Dalton.joe.setObject("boo!");
        try {
            Dalton.joe.setObject(null);
            fail("passed a null argument with aNonNullOf clause");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that anyOf "specializes" the expected instance of an argument.
     */
    @Test
    public void testAnyOfWithAnInheritedClass() {
        // The last error reported by the test
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setObject(anyOf(String.class));
            }
        });
        story.begin();
        Dalton.joe.setObject("$ilver");
        try {
            Dalton.joe.setObject(new Object());
            fail("passed an argument of an unexpected class");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that anyOf "specializes" the expected instance of an argument.
     */
    @Test
    public void testAnyOfWithAnInheritedClassMA() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(Occurrences.atLeast(1)).of(Dalton.joe).setObject(Schemer.anyOf(String.class));
        Dalton.joe.setObject("$ilver");
        try {
            Dalton.joe.setObject(new Object());
            fail("passed an argument of an unexpected class");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that anyOf "specializes" the expected instance of an argument.
     */
    @Test
    public void testAnyOfWithAnInterface() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setObject(anyOf(Dalton.class));
            }
        });
        story.begin();
        Dalton.joe.setObject(Dalton.jack);
        try {
            Dalton.joe.setObject("Jack");
            fail("passed an argument of an unexpected class");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that anyOf "specializes" the expected instance of an argument.
     */
    @Test
    public void testAnyOfWithAnInterfaceMA() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(Occurrences.atLeast(1)).of(Dalton.joe).setObject(Schemer.anyOf(Dalton.class));
        Dalton.joe.setObject(Dalton.jack);
        try {
            Dalton.joe.setObject("Jack");
            fail("passed an argument of an unexpected class");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates the anyOf clause with arrays.
     *
     * <p>
     * In particular, verifies that we actually check each item of an array and
     * not simply the overall array definition.
     * </p>
     */
    @Test
    public void testAnyOfWithAnArray() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(anyOf(String[].class));
            }
        });

        story.begin();
        Dalton.joe.fillPocketWithAPackOf(new String[]{"eurO$", null, "g0ld"});
        Dalton.joe.fillPocketWithAPackOf(new Object[]{"d0llar$", null, "diAmonds"});
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{"ruby", null, Dalton.jack});
            fail("passed an array argument with incompatible items");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates the anyOf clause with arrays.
     *
     * <p>
     * In particular, verifies that we actually check each item of an array and
     * not simply the overall array definition.
     * </p>
     */
    @Test
    public void testAnyOfWithAnArrayMA() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(Occurrences.atLeast(1)).of(Dalton.joe).fillPocketWithAPackOf(Schemer.anyOf(String[].class));

        Dalton.joe.fillPocketWithAPackOf(new String[]{"eurO$", null, "g0ld"});
        Dalton.joe.fillPocketWithAPackOf(new Object[]{"d0llar$", null, "diAmonds"});
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{"ruby", null, Dalton.jack});
            fail("passed an array argument with incompatible items");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates the anyOf clause with arrays of arrays.
     */
    @Test
    public void testAnyOfWithAnArrayOfArrays() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(anyOf(String[][].class));
            }
        });

        story.begin();
        Dalton.joe.fillPocketWithAPackOf(new String[][]{{"d0llar$"},
              {"euros", null}});
        try {
            Dalton.joe.fillPocketWithAPackOf(new int[][]{{1, 2}, {3, 4}});
            fail("invoked method with an incompatible array of arrays");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates the anyOf clause with arrays of arrays.
     */
    @Test
    public void testAnyOfWithAnArrayOfArraysMA() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(Occurrences.atLeast(1)).of(Dalton.joe).fillPocketWithAPackOf(Schemer.anyOf(String[][].class));
        Dalton.joe.fillPocketWithAPackOf(new String[][]{{"d0llar$"},
              {"euros", null}});
        try {
            Dalton.joe.fillPocketWithAPackOf(new int[][]{{1, 2}, {3, 4}});
            fail("invoked method with an incompatible array of arrays");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates the anyOf clause with varargs.
     */
    @Test
    public void testAnyOfWithVarargs() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket(anyOf(Object.class),
                  anyOf(String.class), anyOf(Dalton.class),
                  anyOf(int[].class));
            }
        });

        story.begin();
        Dalton.joe.fillPocket(new Object(), "Damnit!", Dalton.jack, new int[]{1, 2, 3});
        story.end();

        story.begin();
        try {
            Dalton.joe.fillPocket();
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.fillPocket(null, Dalton.william, Dalton.jack, new int[3]);
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.fillPocket(null, "Damnit!", "Again!", new int[3]);
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack,
              new Object[]{1, 2, "three"});
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        // A final corner case: verify that we still parse arrays.
        story.begin();
        Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack, new Object[]{1, 2, 3});
        Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack, null);
        story.end();
    }

    /**
     * Validates the anyOf clause with varargs.
     */
    @Test
    public void testAnyOfWithVarargsMA() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Scenario scenario = new Scenario() {
            {
                expect(Dalton.joe).fillPocket(anyOf(Object.class),
                  anyOf(String.class), anyOf(Dalton.class),
                  anyOf(int[].class));
            }
        };
        Schemer.begin();
        Schemer.append(scenario);
        Dalton.joe.fillPocket(new Object(), "Damnit!", Dalton.jack, new int[]{1, 2, 3});
        Schemer.end();

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket();
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket(null, Dalton.william, Dalton.jack, new int[3]);
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket(null, "Damnit!", "Again!", new int[3]);
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack,
              new Object[]{1, 2, "three"});
            fail("wrong invocation of a method");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        // A final corner case: verify that we still parse arrays.
        Schemer.begin();
        Schemer.append(scenario);
        Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack, new Object[]{1, 2, 3});
        Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack, null);
        Schemer.end();
    }

    /**
     * Verifies that aNonNullOf properly works with varargs.
     */
    @Test
    public void testANonNullOfWithVarargs() {
        // The last error reported by the test
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket(aNonNullOf(Object.class),
                  aNonNullOf(String.class), aNonNullOf(Dalton.class),
                  aNonNullOf(int[].class));
            }
        });
        story.begin();
        Dalton.joe.fillPocket(new Object(), "Damnit!", Dalton.jack, new int[4]);
        story.end();

        story.begin();
        try {
            Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack, new int[4]);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.fillPocket(new Object(), null, Dalton.jack, new int[4]);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.fillPocket(new Object(), "Damnit!", null, new int[4]);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.fillPocket(new Object(), "Damnit!", Dalton.jack, null);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that aNonNullOf properly works with varargs.
     */
    @Test
    public void testANonNullOfWithVarargsMA() {
        // The last error reported by the test.
        ExpectationError lastError = null;

        Scenario scenario = new Scenario() {
            {
                expect(Dalton.joe).fillPocket(aNonNullOf(Object.class),
                  aNonNullOf(String.class), aNonNullOf(Dalton.class),
                  aNonNullOf(int[].class));
            }
        };

        Schemer.begin();
        Schemer.append(scenario);
        Dalton.joe.fillPocket(new Object(), "Damnit!", Dalton.jack, new int[4]);
        Schemer.end();

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket(null, "Damnit!", Dalton.jack, new int[4]);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket(new Object(), null, Dalton.jack, new int[4]);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket(new Object(), "Damnit!", null, new int[4]);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.fillPocket(new Object(), "Damnit!", Dalton.jack, null);
            fail("aNonNullOf let a null argument pass");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }
}
