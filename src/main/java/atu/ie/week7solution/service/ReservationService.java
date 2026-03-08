package atu.ie.week7solution.service;

import atu.ie.week7solution.exception.ReservationConflictException;
import atu.ie.week7solution.exception.ReservationNotFoundException;
import atu.ie.week7solution.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private List<Reservation> reservations = new ArrayList<>();
    private long nextId = 1;

    public Reservation addReservation(Reservation reservation) {

        int newStart = reservation.getStartHour();
        int newEnd = newStart + reservation.getDurationHours();

        for (Reservation existing : reservations) {

            if (existing.getEquipmentTag().equals(reservation.getEquipmentTag()) &&
                    existing.getReservationDate().equals(reservation.getReservationDate())) {

                int existingStart = existing.getStartHour();
                int existingEnd = existingStart + existing.getDurationHours();

                if (existingStart < newEnd && newStart < existingEnd) {
                    throw new ReservationConflictException("Reservation time conflict.");
                }
            }
        }

        reservation.setReservationId(nextId++);
        reservations.add(reservation);

        return reservation;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public Reservation getReservationById(Long id) {

        return reservations.stream()
                .filter(r -> r.getReservationId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
    }
}
