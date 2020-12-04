package moonlodge.frontend.moonlodgefrontend.controller;


import moonlodge.frontend.moonlodgefrontend.contract.dto.BookingDTO;
import moonlodge.frontend.moonlodgefrontend.contract.entitys.Room;
import moonlodge.frontend.moonlodgefrontend.service.HotelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelManagerController {


    @Autowired
    HotelManagerService hotelManagerService;

    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

    @PostMapping("/booking/")
    public BookingDTO createBooking(@RequestBody BookingResponseBody bookingResponseBody) throws RemoteException {
        return this.hotelManagerService.createBooking(bookingResponseBody.getRooms(), bookingResponseBody.getPassportNumbers(), bookingResponseBody.getDateFrom(), bookingResponseBody.getDateTo(), bookingResponseBody.isArrivalIsLate());
    }

    @GetMapping("/booking/{passportNumber}")
    public List<BookingDTO> findBookings(@PathVariable("passportNumber") String passportNumber) throws RemoteException{
        return this.hotelManagerService.findBookings(passportNumber);
    }

    @DeleteMapping("/booking/{bookingId}")
    public Boolean deleteBooking(@PathVariable("bookingId") long bookingId) throws RemoteException {
        return this.hotelManagerService.cancelBooking(bookingId);
    }

    static class BookingResponseBody {
       private List<Room> rooms;
       private String[] passportNumbers;
       private Date dateFrom;
       private Date dateTo;
       private boolean arrivalIsLate;

        public List<Room> getRooms() {
            return rooms;
        }

        public void setRooms(List<Room> rooms) {
            this.rooms = rooms;
        }

        public String[] getPassportNumbers() {
            return passportNumbers;
        }

        public void setPassportNumbers(String[] passportNumbers) {
            this.passportNumbers = passportNumbers;
        }

        public Date getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
        }

        public Date getDateTo() {
            return dateTo;
        }

        public void setDateTo(Date dateTo) {
            this.dateTo = dateTo;
        }

        public boolean isArrivalIsLate() {
            return arrivalIsLate;
        }

        public void setArrivalIsLate(boolean arrivalIsLate) {
            this.arrivalIsLate = arrivalIsLate;
        }
    }


}
