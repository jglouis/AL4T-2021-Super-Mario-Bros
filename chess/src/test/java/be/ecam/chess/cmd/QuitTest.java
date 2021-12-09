package be.ecam.chess.cmd;

import org.junit.jupiter.api.Test;

import java.security.Permission;

import static org.junit.jupiter.api.Assertions.*;

class QuitTest {

    private static class ExitException extends SecurityException {
    }

    private static class NoExitSecurityManager extends SecurityManager
    {
        @Override
        public void checkPermission(Permission perm)
        {
            // allow anything.
        }
        @Override
        public void checkPermission(Permission perm, Object context)
        {
            // allow anything.
        }
        @Override
        public void checkExit(int status)
        {
            super.checkExit(status);
            throw new ExitException();
        }
    }

    @Test
    void execute() {
        Quit quit = new Quit();
        System.setSecurityManager(new NoExitSecurityManager());
        assertThrows(ExitException.class, quit::execute);
    }
}