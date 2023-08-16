/*
 * Kevin Lundeen
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package asun2_lab7;

/**
 * L-system rendering commands.
 * We need two forwards to support various rewrite rules that want to 
 * recurse differently on some forward segments.
 * Similarly, we need an IGNORE to support a type of recursion in some 
 * L-systems.
 * The push and pop are ways of remembering and restoring the pen during
 * the drawing sequence.
 * @author klundeen
 */
public enum RenderCommand {
    FORWARD,    // 'F' in traditional notation
    FORWARD2,   // 'R' in traditional notation
    IGNORE,     // 'X' in traditional notation
    RIGHT,      // '-' in traditional notation
    LEFT,       // '+' in traditional notation
    PUSH,       // '[' in traditional notation
    POP         // ']' in traditional notation
}
