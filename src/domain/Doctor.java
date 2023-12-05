package domain;

import java.io.Serializable;
import java.util.Objects;

public class Doctor implements Identifiable<Integer>, Serializable {
    Integer id;
    private String name, specialty, location;
    double grade;

    public Doctor(Integer id, String name, String specialty, String location, double grade)
    {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.location = location;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + specialty + " " + location + " " + grade;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getSpecialty() {
        return specialty;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
