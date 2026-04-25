package dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты Point.hit()")
class PointTest {

    private static final double R = 2.0;

    private Point makePoint(double x, double y) {
        Point p = new Point();
        p.setX(x);
        p.setY(y);
        p.setR(R);
        return p;
    }

    // Q1: прямоугольник 0 ≤ x ≤ R, 0 ≤ y ≤ R/2
    @Test
    @DisplayName("Q1: центр прямоугольника — попадание")
    void q1_center_hit() { assertTrue(makePoint(1.0, 0.5).hit()); }

    @Test
    @DisplayName("Q1: угол прямоугольника (R, R/2) — попадание")
    void q1_corner_hit() { assertTrue(makePoint(R, R / 2).hit()); }

    @Test
    @DisplayName("Q1: выше прямоугольника — промах")
    void q1_above_miss() { assertFalse(makePoint(1.0, 1.5).hit()); }

    @Test
    @DisplayName("Q1: правее прямоугольника — промах")
    void q1_right_miss() { assertFalse(makePoint(2.5, 0.5).hit()); }

    // Q2: четверть круга x²+y² ≤ R²
    @Test
    @DisplayName("Q2: внутри четверти круга — попадание")
    void q2_inside_hit() { assertTrue(makePoint(-1.0, 1.0).hit()); }

    @Test
    @DisplayName("Q2: на границе окружности — попадание")
    void q2_boundary_hit() { assertTrue(makePoint(0.0, R).hit()); }

    @Test
    @DisplayName("Q2: снаружи окружности — промах")
    void q2_outside_miss() { assertFalse(makePoint(-1.5, 1.5).hit()); }

    // Q4: треугольник y ≥ x - R/2
    @Test
    @DisplayName("Q4: внутри треугольника — попадание")
    void q4_inside_hit() { assertTrue(makePoint(0.5, -0.1).hit()); }

    @Test
    @DisplayName("Q4: на гипотенузе — попадание")
    void q4_hypotenuse_hit() { assertTrue(makePoint(1.0, 0.0).hit()); }

    @Test
    @DisplayName("Q4: ниже гипотенузы — промах")
    void q4_below_miss() { assertFalse(makePoint(2.0, -1.5).hit()); }

    // Q3: всегда промах
    @Test
    @DisplayName("Q3: всегда промах")
    void q3_always_miss() { assertFalse(makePoint(-1.0, -1.0).hit()); }

    // Начало координат
    @Test
    @DisplayName("Начало координат — попадание")
    void origin_hit() { assertTrue(makePoint(0, 0).hit()); }

    // Параметризованные
    @ParameterizedTest(name = "({0}, {1}) → {2}")
    @CsvSource({
            " 1.0,  0.5,  true",
            " 2.0,  1.0,  true",
            "-1.0,  1.0,  true",
            " 0.5, -0.1,  true",
            "-1.0, -1.0,  false",
            " 3.0,  0.5,  false"
    })
    @DisplayName("Параметризованные тесты hit()")
    void parametrized(double x, double y, boolean expected) {
        assertEquals(expected, makePoint(x, y).hit());
    }
}