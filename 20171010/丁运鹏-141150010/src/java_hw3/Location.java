package java_hw3;

import org.omg.PortableInterceptor.LOCATION_FORWARD;

class Location {
    private int x;
    private int y;

    Location() {
        this.x = this.y = 0;
    }

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
}
