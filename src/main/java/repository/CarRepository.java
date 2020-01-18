package repository;

import model.Car;

public interface CarRepository {

    Car find(int id);

    Car create(int id, String brand, String model);

    Car update(Car car);

    void remove(int id);
}
