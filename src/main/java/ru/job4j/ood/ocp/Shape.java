package ru.job4j.ood.ocp;

class Shape {
    class Circle {
        public void drawCircle() {
            System.out.println("Рисую круг");
        }
    }

    class Square {
        public void drawSquare() {
            System.out.println("Рисую квадрат");
        }
    }

    class DrawingApp {
        public void drawAll(Object shape) {
            if (shape instanceof Circle) {
                ((Circle) shape).drawCircle();
            } else if (shape instanceof Square) {
                ((Square) shape).drawSquare();
            }
        }
    }
}

