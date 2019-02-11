package com.defano.hypertalk.ast.expressions.functions;

import com.defano.hypertalk.GuiceTest;
import com.defano.hypertalk.ast.model.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenusFuncTest extends GuiceTest<MenusFunc> {

    @BeforeEach
    public void setUp() {
        initialize(new MenusFunc(mockParserRuleContext));
    }

    @Test
    public void testOnEvaluate() {
        // Setup
        final Value expectedResult = new Value("Menu 0\nMenu 1\nMenu 2");
        Mockito.when(mockWyldCardMenuBar.getMenuCount()).thenReturn(3);
        Mockito.when(mockWyldCardMenuBar.getMenu(0).getText()).thenReturn("Menu 0");
        Mockito.when(mockWyldCardMenuBar.getMenu(1).getText()).thenReturn("Menu 1");
        Mockito.when(mockWyldCardMenuBar.getMenu(2).getText()).thenReturn("Menu 2");

        // Run the test
        final Value result = uut.onEvaluate(mockExecutionContext);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
