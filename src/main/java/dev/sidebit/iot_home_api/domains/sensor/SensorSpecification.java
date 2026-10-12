package dev.sidebit.iot_home_api.domains.sensor;

import dev.sidebit.iot_home_api.domains.sensor.model.SensorEntity;
import dev.sidebit.iot_home_api.domains.sensor.model.StatusSensor;
import org.springframework.data.jpa.domain.Specification;

public class SensorSpecification {
    private SensorSpecification(){}

    public static Specification<SensorEntity> statusEqual(StatusSensor status){
        return (root, query, criteriaBuilder) -> {
           // PATH ATRIBUTO STATUS
//           root.get("status")
//                   query.distinct(true);
//                    query.orderBy()
            // SELECT distinct * FROM SENSOR as root
            //WHERE STATUS = :status
//            criteriaBuilder.asc();
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<SensorEntity> ticketEqual(Integer ticket_id){
        return (root, query, criteriaBuilder) -> {

            return criteriaBuilder.equal(root.get("ticket").get("id"), ticket_id);
        };
    }
}
