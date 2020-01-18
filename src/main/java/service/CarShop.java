package service;

import facade.CarDto;
import facade.ClientDto;
import facade.ResultDto;

public interface CarShop {

    ResultDto buyCar(CarDto carDto, ClientDto clientDto);
}
