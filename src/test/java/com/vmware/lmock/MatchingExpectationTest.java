/* **************************************************************************
 * Copyright (C) 2010-2011 VMware, Inc. All rights reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0.
 * Please see the LICENSE file to review the full text of the Apache License 2.0.
 * You may not use this product except in compliance with the License.
 * ************************************************************************** */
package com.vmware.lmock;

import com.vmware.lmock.exception.ExpectationError;
import static com.vmware.lmock.checker.Occurrences.atLeast;
import static com.vmware.lmock.checker.Occurrences.exactly;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.Test;

import com.vmware.lmock.exception.UnexpectedInvocationError;
import com.vmware.lmock.exception.UnsatisfiedOccurrenceError;
import com.vmware.lmock.impl.Scenario;
import com.vmware.lmock.impl.Story;
import com.vmware.lmock.masquerade.Schemer;

/**
 * Tests of stories using scenarios based on exact (matching) invocations.
 */
public class MatchingExpectationTest {
    /**
     * Verifies that there's no confusion between the prototypes of different
     * methods having the same name.
     */
    @Test
    public void testSeveralInvocationsWithDifferentMethodsWithSameNames() {
        ExpectationError lastError = null;

        // We will run different stories referencing the same scenario.
        Story story = Story.create(new Scenario() {
            {
                // Define a specific return value for each invocation, so that
                // we verify that we passed through the correct one.
                expect(Dalton.joe).ping();
                occurs(exactly(1)).willReturn(0);
                expect(Dalton.joe).ping(Dalton.jack);
                occurs(exactly(1)).willReturn(1);
                expect(Dalton.joe).ping(Dalton.jack, "hello brother!");
                occurs(exactly(1)).willReturn(2);
                expect(Dalton.joe).ping(Dalton.jack, "hello", "brother!");
                occurs(exactly(1)).willReturn(3);
            }
        });

        story.begin();
        try {
            Dalton.joe.ping(Dalton.jack);
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.ping(Dalton.jack, "hello brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        try {
            Dalton.joe.ping(Dalton.jack, "hello", "brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        try {
            Dalton.joe.ping();
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        try {
            Dalton.joe.ping(Dalton.jack, "hello brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        try {
            Dalton.joe.ping(Dalton.jack, "hello", "brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping();
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping(Dalton.jack, "hello", "brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        assertEquals(2, Dalton.joe.ping(Dalton.jack, "hello brother!"));
        try {
            Dalton.joe.ping();
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        assertEquals(2, Dalton.joe.ping(Dalton.jack, "hello brother!"));
        try {
            Dalton.joe.ping(Dalton.jack);
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);

        story.begin();
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        assertEquals(2, Dalton.joe.ping(Dalton.jack, "hello brother!"));
        try {
            Dalton.joe.ping(Dalton.jack, "hello brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that there's no confusion between the prototypes of different
     * methods having the same name.
     */
    @Test
    public void testSeveralInvocationsWithDifferentMethodsWithSameNamesMA() {
        ExpectationError lastError = null;

        // We will run different stories referencing the same scenario.
        Scenario scenario = new Scenario() {
            {
                // Define a specific return value for each invocation, so that
                // we verify that we passed through the correct one.
                expect(Dalton.joe).ping();
                occurs(exactly(1)).willReturn(0);
                expect(Dalton.joe).ping(Dalton.jack);
                occurs(exactly(1)).willReturn(1);
                expect(Dalton.joe).ping(Dalton.jack, "hello brother!");
                occurs(exactly(1)).willReturn(2);
                expect(Dalton.joe).ping(Dalton.jack, "hello", "brother!");
                occurs(exactly(1)).willReturn(3);
            }
        };

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.ping(Dalton.jack);
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.ping(Dalton.jack, "hello brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        try {
            Dalton.joe.ping(Dalton.jack, "hello", "brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        try {
            Dalton.joe.ping();
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        try {
            Dalton.joe.ping(Dalton.jack, "hello brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        try {
            Dalton.joe.ping(Dalton.jack, "hello", "brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping();
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping(Dalton.jack, "hello", "brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        assertEquals(2, Dalton.joe.ping(Dalton.jack, "hello brother!"));
        try {
            Dalton.joe.ping();
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        assertEquals(2, Dalton.joe.ping(Dalton.jack, "hello brother!"));
        try {
            Dalton.joe.ping(Dalton.jack);
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);

        Schemer.begin();
        Schemer.append(scenario);
        assertEquals(0, Dalton.joe.ping());
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        assertEquals(2, Dalton.joe.ping(Dalton.jack, "hello brother!"));
        try {
            Dalton.joe.ping(Dalton.jack, "hello brother!");
            fail("wrong invocation");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates expectations with primitive arguments.
     */
    @Test
    public void testArgsWithPrimitiveTypes() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setBoolean(true);
                occurs(1);
                expect(Dalton.joe).setChar('Z');
                occurs(1);
                expect(Dalton.joe).setByte((byte) 8);
                occurs(1);
                expect(Dalton.joe).setShort((short) 123);
                occurs(1);
                expect(Dalton.joe).setInt(0xdead);
                occurs(1);
                expect(Dalton.joe).setLong(0xbeefL);
                occurs(1);
                expect(Dalton.joe).setFloat((float) 1337.165);
                occurs(1);
                expect(Dalton.joe).setDouble(1337.164);
                occurs(1);
            }
        });
        story.begin();
        Dalton.joe.setBoolean(true);
        Dalton.joe.setChar('Z');
        Dalton.joe.setByte((byte) 8);
        Dalton.joe.setShort((short) 123);
        Dalton.joe.setInt(0xdead);
        Dalton.joe.setLong(0xbeefL);
        Dalton.joe.setFloat((float) 1337.165);
        Dalton.joe.setDouble(1337.164);
        story.end();
    }

    /**
     * Validates expectations with primitive arguments.
     */
    @Test
    public void testArgsWithPrimitiveTypesMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).setBoolean(true);
        Dalton.joe.setBoolean(true);
        Schemer.willInvoke(1).of(Dalton.joe).setChar('Z');
        Dalton.joe.setChar('Z');
        Schemer.willInvoke(1).of(Dalton.joe).setByte((byte) 8);
        Dalton.joe.setByte((byte) 8);
        Schemer.willInvoke(1).of(Dalton.joe).setShort((short) 123);
        Dalton.joe.setShort((short) 123);
        Schemer.willInvoke(1).of(Dalton.joe).setInt(0xdead);
        Dalton.joe.setInt(0xdead);
        Schemer.willInvoke(1).of(Dalton.joe).setLong(0xbeefL);
        Dalton.joe.setLong(0xbeefL);
        Schemer.willInvoke(1).of(Dalton.joe).setFloat((float) 1337.165);
        Dalton.joe.setFloat((float) 1337.165);
        Schemer.willInvoke(1).of(Dalton.joe).setDouble(1337.164);
        Dalton.joe.setDouble(1337.164);
        Schemer.end();
    }

    /**
     * Validates an expectation with a null argument.
     */
    @Test
    public void testArgsWithNullArgument() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).ping(null);
                expect().occurs(exactly(2)).willReturn(1);
            }
        });

        story.begin();
        assertEquals(1, Dalton.joe.ping(null));
        try {
            Dalton.joe.ping(Dalton.jack);
            fail("expected null, invoked with non-null");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates an expectation with a null argument.
     */
    @Test
    public void testArgsWithNullArgumentMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(2).willReturn(1).when(Dalton.joe).ping(null);

        assertEquals(1, Dalton.joe.ping(null));
        try {
            Dalton.joe.ping(Dalton.jack);
            fail("expected null, invoked with non-null");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates an expectation with a non-null argument.
     */
    @Test
    public void testArgsWithNonNullArgument() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).ping(Dalton.jack);
                expect().occurs(exactly(2)).willReturn(1);
            }
        });

        story.begin();
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping(null);
            fail("expected non-null, invoked with null");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates an expectation with a non-null argument.
     */
    @Test
    public void testArgsWithNonNullArgumentMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(2).willReturn(1).when(Dalton.joe).ping(Dalton.jack);
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping(null);
            fail("expected non-null, invoked with null");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates an expectation with a simple object.
     *
     * <p>
     * The comparison should use the <code>equals</code> method.
     * </p>
     */
    @Test
    public void testArgsWithObject() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).setObject("hurray!");
                expect().occurs(exactly(2));
            }
        });

        story.begin();
        Dalton.joe.setObject("hurray!");
        try {
            Dalton.joe.setObject("kurrai!");
            fail("called a method with an invalid argument");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates an expectation with a simple object.
     *
     * <p>
     * The comparison should use the <code>equals</code> method.
     * </p>
     */
    @Test
    public void testArgsWithObjectMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(2).of(Dalton.joe).setObject("hurray!");
        Dalton.joe.setObject("hurray!");
        try {
            Dalton.joe.setObject("kurrai!");
            fail("called a method with an invalid argument");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates an expectation with a mock as argument.
     */
    @Test
    public void testArgsWithMock() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).ping(Dalton.jack);
                expect().occurs(exactly(2)).willReturn(1);
            }
        });

        story.begin();
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping(Dalton.william);
            fail("called a method with an invalid argument");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates an expectation with a mock as argument.
     */
    @Test
    public void testArgsWithMockMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(2).willReturn(1).when(Dalton.joe).ping(Dalton.jack);
        assertEquals(1, Dalton.joe.ping(Dalton.jack));
        try {
            Dalton.joe.ping(Dalton.william);
            fail("called a method with an invalid argument");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that expectation with multiple parameters are correctly checked.
     */
    @Test
    public void testArgsWithMultipleParameters() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).ping(Dalton.averell, "c'mon!");
                expect().occurs(exactly(2)).willReturn(154);
            }
        });

        story.begin();
        assertEquals(154, Dalton.joe.ping(Dalton.averell, "c'mon!"));
        try {
            Dalton.joe.ping(Dalton.averell, "c''mon!");
            fail("called a method with an invalid argument");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that expectation with multiple parameters are correctly checked.
     */
    @Test
    public void testArgsWithMultipleParametersMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(2).willReturn(154).when(Dalton.joe).ping(Dalton.averell, "c'mon!");
        assertEquals(154, Dalton.joe.ping(Dalton.averell, "c'mon!"));
        try {
            Dalton.joe.ping(Dalton.averell, "c''mon!");
            fail("called a method with an invalid argument");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that a valid invocation implying an array is correctly handled.
     *
     * <p>
     * The checker should validate the whole contents of the array.
     * </p>
     */
    @Test
    public void testArgsWithAnArray() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(
                  new Object[]{null, 1, Dalton.jack, "boo!"});
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.jack, "boo!"});
        story.end();
    }

    /**
     * Verifies that a valid invocation implying an array is correctly handled.
     *
     * <p>
     * The checker should validate the whole contents of the array.
     * </p>
     */
    @Test
    public void testArgsWithAnArrayMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocketWithAPackOf(new Object[]{null, 1,
              Dalton.jack, "boo!"});
        Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.jack, "boo!"});
        Schemer.end();
    }

    /**
     * Verifies that the length of arrays is tested when having such
     * expectations.
     */
    @Test
    public void testArgsWithArrayInvokedWithAShorterArray() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(
                  new Object[]{null, 1, Dalton.jack, "boo!"});
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.jack});
            fail("invoked a method with a different array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that the length of arrays is tested when having such
     * expectations.
     */
    @Test
    public void testArgsWithArrayInvokedWithAShorterArrayMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocketWithAPackOf(new Object[]{null, 1,
              Dalton.jack, "boo!"});
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.jack});
            fail("invoked a method with a different array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that the length of arrays is tested when having such
     * expectations.
     */
    @Test
    public void testArgsWithArrayInvokedWithALongerArray() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(
                  new Object[]{null, 1, Dalton.jack, "boo!"});
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.jack, "boo!",
                  "yop!"});
            fail("invoked a method with a different array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that the length of arrays is tested when having such
     * expectations.
     */
    @Test
    public void testArgsWithArrayInvokedWithALongerArrayMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocketWithAPackOf(new Object[]{null, 1,
              Dalton.jack, "boo!"});
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.jack, "boo!",
                  "yop!"});
            fail("invoked a method with a different array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that array contents are actually checked during invocations.
     */
    @Test
    public void testArgsWithArrayInvokedWithADifferentArray() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(
                  new Object[]{null, 1, Dalton.jack, "boo!"});
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.william, "boo!"});
            fail("invoked a method with a different array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that array contents are actually checked during invocations.
     */
    @Test
    public void testArgsWithArrayInvokedWithADifferentArrayMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocketWithAPackOf(new Object[]{null, 1,
              Dalton.jack, "boo!"});
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{null, 1, Dalton.william, "boo!"});
            fail("invoked a method with a different array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates the case of null arrays in expectations.
     */
    @Test
    public void testArgsWithNullArrayExpected() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(null);
                expect().occurs(exactly(2));
            }
        });

        story.begin();
        Dalton.joe.fillPocketWithAPackOf(null);
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{"gold", "silver"});
            fail("invoked a method with an unexpected non-null array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates the case of null arrays in expectations.
     */
    @Test
    public void testArgsWithNullArrayExpectedMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(2).of(Dalton.joe).fillPocketWithAPackOf(null);
        Dalton.joe.fillPocketWithAPackOf(null);
        try {
            Dalton.joe.fillPocketWithAPackOf(new Object[]{"gold", "silver"});
            fail("invoked a method with an unexpected non-null array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates the case of null arrays in invocations.
     */
    @Test
    public void testArgsWithNullArrayInvoked() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(new Object[]{1, 2, 3});
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocketWithAPackOf(null);
            fail("invoked a method with an unexpected null array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates the case of null arrays in invocations.
     */
    @Test
    public void testArgsWithNullArrayInvokedMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket(new Object[]{1, 2, 3});
        try {
            Dalton.joe.fillPocketWithAPackOf(null);
            fail("invoked a method with an unexpected null array");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that there's no corner cases with null arrays.
     */
    @Test
    public void testArgsWithANullArray() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(null);
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        Dalton.joe.fillPocketWithAPackOf(null);
        story.end();
    }

    /**
     * Verifies that there's no corner cases with null arrays.
     */
    @Test
    public void testArgsWithANullArrayMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocketWithAPackOf(null);
        Dalton.joe.fillPocketWithAPackOf(null);
        Schemer.end();
    }

    /**
     * Plays with array to verify that the invocation checker actually verifies
     * the data each time.
     */
    @Test
    public void testExpectationsWithAnArray() {
        final Object[] stuff = {"nothing", "nada", "rien"};
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocketWithAPackOf(stuff);
            }
        });
        story.begin();
        // Before checking, change the stuff contents. Since we basically
        // reference the object table, this modification should not impact
        // the expectation (in other words, we basically reference the array
        // object, not its contents).
        stuff[0] = "things";
        stuff[1] = "cosas";
        stuff[2] = "choses";
        Dalton.joe.fillPocketWithAPackOf(stuff);
        story.end();
    }

    /**
     * Plays with array to verify that the invocation checker actually verifies
     * the data each time.
     */
    @Test
    public void testExpectationsWithAnArrayMA() {
        final Object[] stuff = {"nothing", "nada", "rien"};
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocketWithAPackOf(stuff);
        // Before checking, change the stuff contents. Since we basically
        // reference the object table, this modification should not impact
        // the expectation (in other words, we basically reference the array
        // object, not its contents).
        stuff[0] = "things";
        stuff[1] = "cosas";
        stuff[2] = "choses";
        Dalton.joe.fillPocketWithAPackOf(stuff);
        Schemer.end();
    }

    /**
     * Validates a story with varargs.
     */
    @Test
    public void testArgsWithVarargs() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket("gold", "silver", 66.77, null, Dalton.jack);
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        Dalton.joe.fillPocket("gold", "silver", 66.77, null, Dalton.jack);
        story.end();
    }

    /**
     * Validates a story with varargs.
     */
    @Test
    public void testArgsWithVarargsMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket("gold", "silver", 66.77, null,
          Dalton.jack);
        Dalton.joe.fillPocket("gold", "silver", 66.77, null, Dalton.jack);
        Schemer.end();
    }

    /**
     * Verifies that an expectation with an empty varargs list is correctly
     * processed.
     */
    @Test
    public void testArgsWithEmptyVarargs() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket();
                expect().occurs(exactly(2));
            }
        });

        story.begin();
        Dalton.joe.fillPocket();
        try {
            Dalton.joe.fillPocket(154);
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that an expectation with an empty varargs list is correctly
     * processed.
     */
    @Test
    public void testArgsWithEmptyVarargsMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(2).of(Dalton.joe).fillPocket();
        Dalton.joe.fillPocket();
        try {
            Dalton.joe.fillPocket(154);
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that an expectation with varargs is not satisfied when the
     * invocation has no arguments.
     */
    @Test
    public void testArgsWithVarargsAndEmptyInvocation() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket("dutchy", "skippy");
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocket();
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that an expectation with varargs is not satisfied when the
     * invocation has no arguments.
     */
    @Test
    public void testArgsWithVarargsAndEmptyInvocationMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket("dutchy", "skippy");
        try {
            Dalton.joe.fillPocket();
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates an invalid invocation with varargs: not enough arguments in the
     * invocation.
     */
    @Test
    public void testArgsWithVarargsInvokedWithLessArguments() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket("some", "more");
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocket("some");
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates an invalid invocation with varargs: not enough arguments in the
     * invocation.
     */
    @Test
    public void testArgsWithVarargsInvokedWithLessArgumentsMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket("some", "more");
        try {
            Dalton.joe.fillPocket("some");
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Validates an invalid invocation with varargs: too many arguments in the
     * invocation.
     */
    @Test
    public void testArgsWithVarargsInvokedWithMoreArguments() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket("some", "more");
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocket("some", "more", "and few others");
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Validates an invalid invocation with varargs: too many arguments in the
     * invocation.
     */
    @Test
    public void testArgsWithVarargsInvokedWithMoreArgumentsMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket("some", "more");
        try {
            Dalton.joe.fillPocket("some", "more", "and few others");
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that wrong invocations implying varargs are correctly detected.
     *
     * <p>
     * We take the opportunity to check that an array within varargs is actually
     * checked.
     * </p>
     */
    @Test
    public void testArgsWithVarargsInvokedWithWrongArguments() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket("a bit of",
                  new Integer[]{1, 2, 3, 4}, Dalton.jack);
            }
        });

        story.begin();
        try {
            Dalton.joe.fillPocket("a bit of", new Integer[]{1, 2, 3, 5}, Dalton.jack);
            fail("invoked a method with the wrong arguments");
        } catch (UnexpectedInvocationError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Verifies that wrong invocations implying varargs are correctly detected.
     *
     * <p>
     * We take the opportunity to check that an array within varargs is actually
     * checked.
     * </p>
     */
    @Test
    public void testArgsWithVarargsInvokedWithWrongArgumentsMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket("a bit of", new Integer[]{1, 2,
              3, 4}, Dalton.jack);
        try {
            Dalton.joe.fillPocket("a bit of", new Integer[]{1, 2, 3, 5}, Dalton.jack);
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Checks an invocation with a set of matching null objects.
     *
     * <p>
     * Also play a little bit with the casting of null objects to verify that we
     * tolerate that.
     * </p>
     */
    @Test
    public void testArgsWithVarargsWithMatchingNullObjects() {
        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.joe).fillPocket(null, null, (String) null,
                  new Object[]{null, null});
            }
        });

        story.begin();
        Dalton.joe.fillPocket((String) null, null, null, new Object[]{null, null});
        story.end();
    }

    /**
     * Checks an invocation with a set of matching null objects.
     *
     * <p>
     * Also play a little bit with the casting of null objects to verify that we
     * tolerate that.
     * </p>
     */
    @Test
    public void testArgsWithVarargsWithMatchingNullObjectsMA() {
        Schemer.begin();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket(null, null, (String) null,
          new Object[]{null, null});
        Dalton.joe.fillPocket((String) null, null, null, new Object[]{null, null});
        Schemer.end();
    }

    /**
     * Combines a returned array of object with a method with varargs.
     */
    @Test
    public void testExpectationsWithReturnedListOfObjects() {
        Story story = Story.create(new Scenario() {
            {
                Object listOfStuff = new Object[]{null,
                    new String[]{"hello", "world"}, 1, "again"};
                expect(Dalton.joe).emptyPocket();
                expect().willReturn(listOfStuff).occurs(atLeast(1));
                // Express the contents explicitly, so that there is no
                // reference at all to this list of stuff...
                expect(Dalton.jack).fillPocket(null,
                  new String[]{"hello", "world"}, 1, "again");
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        assertNotNull(Dalton.joe.emptyPocket());
        assertEquals(4, Dalton.joe.emptyPocket().length);
        Dalton.jack.fillPocket(Dalton.joe.emptyPocket());
        story.end();
    }

    /**
     * Combines a returned array of object with a method with varargs.
     */
    @Test
    public void testExpectationsWithReturnedListOfObjectsMA() {
        Object listOfStuff = new Object[]{null,
            new String[]{"hello", "world"}, 1, "again"};
        Schemer.begin();
        Schemer.willInvoke(atLeast(1)).willReturn(listOfStuff).when(Dalton.joe).
          emptyPocket();
        assertNotNull(Dalton.joe.emptyPocket());
        assertEquals(4, Dalton.joe.emptyPocket().length);

        // Express the contents explicitly, so that there is no
        // reference at all to this list of stuff...
        Schemer.willInvoke(1).of(Dalton.jack).fillPocket(null, new String[]{"hello",
              "world"}, 1, "again");
        Dalton.jack.fillPocket(Dalton.joe.emptyPocket());
        Schemer.end();
    }

    /**
     * Combines a returned array of object with a method with varargs.
     */
    @Test
    public void testExpectationsWithUnmatchingReturnedListOfObjects() {
        ExpectationError lastError = null;

        Story story = Story.create(new Scenario() {
            {
                Object listOfStuff = new Object[]{null, Integer.valueOf(4),
                    1, null, "again"};

                expect(Dalton.joe).emptyPocket();
                expect().willReturn(listOfStuff).occurs(atLeast(1));

                expect(Dalton.jack).fillPocket(null,
                  new String[]{"hello", "world"}, 1, null, "again");
                expect().occurs(exactly(1));
            }
        });

        story.begin();
        assertNotNull(Dalton.joe.emptyPocket());
        assertEquals(5, Dalton.joe.emptyPocket().length);
        try {
            Dalton.jack.fillPocket(Dalton.joe.emptyPocket());
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(story, lastError);
    }

    /**
     * Combines a returned array of object with a method with varargs.
     */
    @Test
    public void testExpectationsWithUnmatchingReturnedListOfObjectsMA() {
        ExpectationError lastError = null;

        Schemer.begin();
        Object listOfStuff = new Object[]{null, Integer.valueOf(4), 1, null,
            "again"};

        Schemer.willInvoke(atLeast(1)).willReturn(listOfStuff).when(Dalton.joe).
          emptyPocket();
        assertNotNull(Dalton.joe.emptyPocket());
        assertEquals(5, Dalton.joe.emptyPocket().length);

        Schemer.willInvoke(1).of(Dalton.jack).fillPocket(null, new String[]{"hello",
              "world"}, 1, null,
          "again");
        try {
            Dalton.jack.fillPocket(Dalton.joe.emptyPocket());
            fail("invoked a method with the wrong arguments");
        } catch (UnsatisfiedOccurrenceError e) {
            lastError = e;
        }
        LMAsserts.assertEndReportsError(lastError);
    }

    /**
     * Verifies that there is no confusion between proxies and mocks when
     * testing arguments.
     */
    @Test
    public void testExpectationsWithProxy() {
        final Object nasty = Proxy.newProxyInstance(this.getClass().
          getClassLoader(), new Class[]{List.class},
          new InvocationHandler() {
              @Override
              public Object invoke(Object arg0, Method arg1, Object[] arg2)
                throws Throwable {
                  // The test will imply invocations to equals.
                  if (arg1.getName().equals("equals")) {
                      return true;
                  } else if (arg1.getName().equals("toString")) {
                      return "proxy";
                  } else {
                      fail("don't know how I arrived there, but sure I am - method="
                        + arg1);
                      return null;
                  }
              }
          });
        assertNotNull(nasty);

        Story story = Story.create(new Scenario() {
            {
                expect(Dalton.jack).emptyPocket();
                willReturn(new Object[]{nasty}).occurs(exactly(1));
                expect(Dalton.joe).fillPocket(nasty);
                occurs(exactly(1));
            }
        });

        story.begin();
        Dalton.joe.fillPocket(Dalton.jack.emptyPocket()[0]);
        story.end();
    }

    /**
     * Verifies that there is no confusion between proxies and mocks when
     * testing arguments.
     */
    @Test
    public void testExpectationsWithProxyMA() {
        final Object nasty = Proxy.newProxyInstance(this.getClass().
          getClassLoader(), new Class[]{List.class},
          new InvocationHandler() {
              @Override
              public Object invoke(Object arg0, Method arg1, Object[] arg2)
                throws Throwable {
                  // The test will imply invocations to equals.
                  if (arg1.getName().equals("equals")) {
                      return true;
                  } else if (arg1.getName().equals("toString")) {
                      return "proxy";
                  } else {
                      fail("don't know how I arrived there, but sure I am - method="
                        + arg1);
                      return null;
                  }
              }
          });
        assertNotNull(nasty);

        Schemer.begin();
        Schemer.willInvoke(1).willReturn(new Object[]{nasty}).when(Dalton.jack).
          emptyPocket();
        Schemer.willInvoke(1).of(Dalton.joe).fillPocket(nasty);
        Dalton.joe.fillPocket(Dalton.jack.emptyPocket()[0]);
        Schemer.end();
    }
}
