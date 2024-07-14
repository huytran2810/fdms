package org.SWP391_FUHL_SE1825.FDMS.repository;

import org.SWP391_FUHL_SE1825.FDMS.dto.respone.PaymentRespone;
import org.SWP391_FUHL_SE1825.FDMS.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(
            value = "SELECT p.payment_id AS paymentId, u.full_name AS fullName, b.bed_name AS bedName, s.semester_name AS semesterName, p.status, p.amount, p.created_at AS createdAt " +
                    "FROM payment p " +
                    "JOIN student_secondary_information st ON p.student_id = st.student_id " +
                    "JOIN user u ON u.id = st.student_id " +
                    "JOIN semester s ON s.semester_id = p.semester_id " +
                    "JOIN bed_booked book ON book.student_id = st.student_id " +
                    "JOIN bed b ON b.bed_id = book.bed_id",
            countQuery = "SELECT COUNT(*) " +
                    "FROM payment p " +
                    "JOIN student_secondary_information st ON p.student_id = st.student_id " +
                    "JOIN user u ON u.id = st.student_id " +
                    "JOIN semester s ON s.semester_id = p.semester_id " +
                    "JOIN bed_booked book ON book.student_id = st.student_id " +
                    "JOIN bed b ON b.bed_id = book.bed_id",
            nativeQuery = true
    )
    Page<PaymentRespone> findAllPaymentResponse(Pageable pageable);
}
