public class Circle {
    Point center;
    int radius;

    double getArea(){
        return Math.PI * radius * radius;
    }

    double getParameter(){
        return 2 * Math.PI * radius;
    }

    boolean isOverlapping(Circle other){
        int xDelta = other.center.x - this.center.x;
        int yDelta = other.center.y - this.center.y;
        int distanceSquared = xDelta * xDelta + yDelta * yDelta;

        int sumOfRadiusSquared = (this.radius + other.radius) * (this.radius + other.radius);

        if(distanceSquared > sumOfRadiusSquared){
            return false;
        } else {
            return true;
        }
    }
}

