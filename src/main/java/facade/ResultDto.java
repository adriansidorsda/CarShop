package facade;

public class ResultDto {
    private Status status;
    private String message;
    private CarDto carDto;

    public static ResultDto success(CarDto carDto) {
        return new ResultDto(Status.SUCCESS, "", carDto);
    }

    public static ResultDto paymentError(String message) {
        return new ResultDto(Status.PAYMENT_ERROR, message, null);
    }

    public static ResultDto carNotFound(String message) {
        return new ResultDto(Status.CAR_NOT_FOUND, message,  null);
    }

    public ResultDto(Status status, String message, CarDto carDto) {
        this.status = status;
        this.message = message;
        this.carDto = carDto;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public CarDto getCarDto() {
        return carDto;
    }
}
