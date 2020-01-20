package service;

import facade.CarDto;
import facade.ClientDto;
import facade.ResultDto;
import facade.Status;
import model.Car;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import remote.NotEnoughMoneyException;
import remote.PaymentService;
import repository.CarRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarShopImplTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private PaymentService paymentService;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private CarShop testee;

    @Before
    public void setup() {
        testee = new CarShopImpl(carRepository, paymentService);
    }


    @Test
    public void testSuccess() {
        //given
        CarDto carDto = new CarDto(1, 200.0);
        ClientDto clientDto = new ClientDto("name", "lastName", "1234466778");
        when(carRepository.find(eq(1))).thenReturn(new Car(1,"fiat", "panda", 200.0));

        //when
        ResultDto result = testee.buyCar(carDto, clientDto);

        //then
        assertEquals(Status.SUCCESS, result.getStatus());
        assertEquals("", result.getMessage());
        assertNotNull(result.getCarDto());
    }

    @Test
    public void testCarNotFound() {
        //given
        CarDto carDto = new CarDto(1, 200.0);
        ClientDto clientDto = new ClientDto("name", "lastName", "1234466778");
        when(carRepository.find(eq(1))).thenReturn(null);

        //when
        ResultDto result = testee.buyCar(carDto, clientDto);

        //then
        assertEquals(Status.CAR_NOT_FOUND, result.getStatus());
        assertEquals("Car not exist!", result.getMessage());
        assertNull(result.getCarDto());
    }

    @Test
    public void testPaymentError() throws NotEnoughMoneyException {
        //given
        CarDto carDto = new CarDto(1, 200.0);
        ClientDto clientDto = new ClientDto("name", "lastName", "1234466778");
        when(carRepository.find(eq(1))).thenReturn(new Car(1,"fiat", "panda", 200.0));
        doThrow(NotEnoughMoneyException.class).when(paymentService).pay(anyString(), anyDouble());

        //when
        ResultDto result = testee.buyCar(carDto, clientDto);

        //then
        assertEquals(Status.PAYMENT_ERROR, result.getStatus());
        assertNull(result.getCarDto());
    }

    @Test
    public void testBuyCarWhenExceptionThrown() {
        //given
        CarDto carDto = new CarDto(1, 200.0);
        ClientDto clientDto = null;
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("CarDto or ClientDto is null!");

        //when
        ResultDto result = testee.buyCar(carDto, clientDto);
    }
}
