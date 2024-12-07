package com.prueba.tecnica.inditex.utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.junit.jupiter.MockitoExtension;

public class CheckListExtension extends MockitoExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        System.out.println("🕒 Running: " + context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        boolean testPassed = context.getExecutionException().isEmpty();
        System.out.println(testPassed ? "✅ PASSED: " + context.getDisplayName()
                : "❌ FAILED: " + context.getDisplayName());
    }
}
