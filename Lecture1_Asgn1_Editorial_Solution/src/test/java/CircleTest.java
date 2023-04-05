import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
    void xExists(){
        try {
            Field xField = point.getClass().getDeclaredField("x");
            assertEquals(xField.getType().toString(), "int");
        } catch (NoSuchFieldException e) {
            fail("x not found");
        }
    }

    @Test
    void yExists(){
        try {
            Field yField = point.getClass().getDeclaredField("y");
            assertEquals(yField.getType().toString(), "int");
        } catch (NoSuchFieldException e) {
            fail("y not found");
        }
    }


    @Test
    void centerExists(){
        try {
            Field heightField = circle.getClass().getDeclaredField("center");
            assertEquals(heightField.getType().toString(), "class Point");
        } catch (NoSuchFieldException e) {
            fail("center not found");
        }
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

    @Test
    void getAreaMethodExists(){
        try {
            Method getAreaMethod = circle.getClass().getDeclaredMethod("getArea");
        } catch (NoSuchMethodException e) {
            fail("get Area method not found");
        }
    }

    @Test
    void getAreaMethodSignatureCheck() throws NoSuchMethodException {
        Method getAreaMethod = circle.getClass().getDeclaredMethod("getArea");
        assertEquals(getAreaMethod.toString(), "double Circle.getArea()");
    }

    @Test
    void getParameterMethodExists(){
        try {
            Method getParameterMethod = circle.getClass().getDeclaredMethod("getParameter");
        } catch (NoSuchMethodException e) {
            fail("get Parameter method not found");
        }
    }

    @Test
    void getParameterMethodSignatureCheck() throws NoSuchMethodException {
        Method getAreaMethod = circle.getClass().getDeclaredMethod("getParameter");
        assertEquals(getAreaMethod.toString(), "double Circle.getParameter()");
    }

    @Test
    void isOverlappingMethodExists(){
        try {
            Method isOverlappingMethod = circle.getClass().getDeclaredMethod("isOverlapping", Circle.class);
        } catch (NoSuchMethodException e) {
            fail("isOverlapping method not found");
        }
    }

    @Test
    void isOverlappingMethodSignatureCheck() throws NoSuchMethodException {
        Method isOverlappingMethod = circle.getClass().getDeclaredMethod("isOverlapping",  Circle.class);
        assertEquals(isOverlappingMethod.toString(), "boolean Circle.isOverlapping(Circle)");
    }


    @Test
    void getArea() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InvocationTargetException {
        Field radiusField = circle.getClass().getDeclaredField("radius");
        radiusField.setAccessible(true);
        radiusField.set(circle, 5);

        Method getAreaMethod = circle.getClass().getDeclaredMethod("getArea");
        getAreaMethod.setAccessible(true);
        double area = (double) getAreaMethod.invoke(circle);

        assertEquals(78.53981633974483, area, 0.0001);
    }
    @Test
    void getParameter() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // Set radius
        Field radiusField = circle.getClass().getDeclaredField("radius");
        radiusField.setAccessible(true);
        radiusField.set(circle, 5);

        // Call getParameter() using reflection
        Method getParameterMethod = circle.getClass().getDeclaredMethod("getParameter");
        getParameterMethod.setAccessible(true);
        double parameter = (double) getParameterMethod.invoke(circle);

        // Verify the result
        assertEquals(31.41592653589793, parameter, 0.00001);
    }
    @Test
    void isOverlappingTest() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();

        // Set the radius and center of circle1 using reflection
        Field radiusField1 = circle1.getClass().getDeclaredField("radius");
        radiusField1.setAccessible(true);
        radiusField1.set(circle1, 5);

        Point point = new Point();
        Field xField = point.getClass().getDeclaredField("x");
        xField.setAccessible(true);
        xField.set(point, 3);

        Field yField = point.getClass().getDeclaredField("y");
        yField.setAccessible(true);
        yField.set(point, 4);

        Field centerField1 = circle1.getClass().getDeclaredField("center");
        centerField1.setAccessible(true);
        centerField1.set(circle1, point);

        // Set the radius and center of circle2 using reflection
        Field radiusField2 = circle2.getClass().getDeclaredField("radius");
        radiusField2.setAccessible(true);
        radiusField2.set(circle2, 3);

        Point point1 = new Point();
        Field xField1 = point1.getClass().getDeclaredField("x");
        xField1.setAccessible(true);
        xField1.set(point1, 3);

        Field yField1 = point1.getClass().getDeclaredField("y");
        yField1.setAccessible(true);
        yField1.set(point1, 4);

        Field centerField2 = circle2.getClass().getDeclaredField("center");
        centerField2.setAccessible(true);
        centerField2.set(circle2, point1);

        // Call the isOverlapping method using reflection
        Method isOverlappingMethod = circle1.getClass().getDeclaredMethod("isOverlapping", Circle.class);
        isOverlappingMethod.setAccessible(true);
        boolean isOverlapping = (boolean) isOverlappingMethod.invoke(circle1, circle2);

        assertTrue(isOverlapping);

        // Change the position of circle2 to make it not overlap with circle1
        xField1.set(point1, 3);
        yField1.set(point1, 100);
        centerField2.set(circle2, point1);
        isOverlapping = (boolean) isOverlappingMethod.invoke(circle1, circle2);

        assertFalse(isOverlapping);
    }



}
