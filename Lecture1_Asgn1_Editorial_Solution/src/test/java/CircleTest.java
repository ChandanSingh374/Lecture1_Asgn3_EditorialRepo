import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
public class CircleTest {
    private Circle circle;
    private ByteArrayOutputStream outContent;

    private Point point;

    @BeforeEach
    void setUp() {
        circle = new Circle();
        point = new Point();
    }

    @Test
    public void testGetArea() {
        Circle c = new Circle();
        c.radius = 4;
        assertEquals(Math.PI * 16, c.getArea(), 0.001);
    }

    @Test
    public void testGetParameter() {
        Circle c = new Circle();
        c.radius = 4;
        assertEquals(Math.PI * 8, c.getParameter(), 0.001);
    }


    @Test
    public void testRadiusExists() {
        try {
            Field radiusField = circle.getClass().getDeclaredField("radius");
            assertEquals(radiusField.getType().toString(), "int");
        } catch (NoSuchFieldException e) {
            fail("radius data member of type int not found");
        }
    }

}