package service;

import facade.CarDto;
import facade.ClientDto;
import facade.ResultDto;

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
      // TODO implement this method
    }
}
