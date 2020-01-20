package service;

import facade.CarDto;
import facade.ClientDto;
import facade.ResultDto;

import model.Car;
import remote.NotEnoughMoneyException;
import remote.PaymentService;
import repository.CarRepository;

public class CarShopImpl implements CarShop {

    private CarRepository carRepository;
    private PaymentService paymentService;

    public CarShopImpl(CarRepository carRepository, PaymentService paymentService) {
        this.carRepository = carRepository;
        this.paymentService = paymentService;
    }

    @Override
    public ResultDto buyCar(CarDto carDto, ClientDto clientDto) {

        if (carDto == null || clientDto == null) {
            throw new IllegalArgumentException("CarDto or ClientDto is null!");
        }

        Car car = carRepository.find(carDto.getId());

        if (car == null) {
            return ResultDto.carNotFound("Car not exist!");
        }

        try {
            paymentService.pay(clientDto.getPesel(), carDto.getPrice());
        } catch (NotEnoughMoneyException e) {
            return ResultDto.paymentError(e.getMessage());
        }

        return ResultDto.success(carDto);
    }
}
