package moonlodge.frontend.moonlodgefrontend;

import moonlodge.frontend.moonlodgefrontend.contract.interfaces.HotelManagerInterface;
import moonlodge.frontend.moonlodgefrontend.controller.HotelManagerController;
import moonlodge.frontend.moonlodgefrontend.service.HotelManagerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.rmi.RemoteException;


@SpringBootApplication
@ComponentScan(basePackageClasses = {HotelManagerController.class, HotelManagerService.class})
public class MoonlodgeFrontendApplication {

	@Bean(name="rmiProxy")
	RmiProxyFactoryBean rimProxy(){
		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(HotelManagerInterface.class);
		bean.setServiceUrl("rmi://localhost:1099/moonlodge");
		return bean;
	}

	public static void main(String[] args) throws RemoteException {
		HotelManagerInterface hotelManagerInterface = SpringApplication.run(MoonlodgeFrontendApplication.class, args).getBean(HotelManagerInterface.class);
		System.out.println(hotelManagerInterface.cancelBooking(1));
	}

}
