package microservices.department_service.model;

public record Employee (
        String id,
        Long departmentId,
        String name,
        int age,
        String position
){}
