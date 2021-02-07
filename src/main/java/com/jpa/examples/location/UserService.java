package com.jpa.examples.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class UserService {

//    @Autowired
//    private TownRepository townRepository;
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    public Mono<Employee> createUser(Employee employee){
//        return townRepository.save(employee);
//    }
//
//    public Flux<Employee> getAllUsers() {
//        return townRepository.findAll();
//    }
//
//    public Mono<Employee> findById(Long userId){
//        return townRepository.findById(userId);
//    }
//
//    public Mono<Employee> updateUser(Long userId, Employee employee){
//        return townRepository.findById(userId)
//                .flatMap(dbUser -> {
//                    dbUser.setAge(employee.getAge());
//                    dbUser.setSalary(employee.getSalary());
//                    return townRepository.save(dbUser);
//                });
//    }
//
//    public Mono<Employee> deleteUser(Long userId){
//        return townRepository.findById(userId)
//                .flatMap(existingUser -> townRepository.delete(existingUser)
//                        .then(Mono.just(existingUser)));
//    }
//
//    public Flux<Employee> findUsersByAge(int age){
//        return townRepository.findByAge(age);
//    }
//
//    public Flux<Employee> fetchUsers(List<Long> userIds) {
//        return Flux.fromIterable(userIds)
//                .parallel()
//                .runOn(Schedulers.boundedElastic())
//                .flatMap(i -> findById(i))
//                .ordered((u1, u2) -> u2.getId() - u1.getId());
//    }

//    private Mono<Department> getDepartmentByUserId(Long userId){
//        return departmentRepository.findByUserId(userId);
//    }
//
//    public Mono<UserDepartmentDto> fetchUserAndDepartment(Long userId){
//        Mono<User> user = findById(userId).subscribeOn(Schedulers.elastic());
//        Mono<Department> department = getDepartmentByUserId(userId).subscribeOn(Schedulers.elastic());
//        return Mono.zip(user, department, userDepartmentDTOBiFunction);
//    }

//    private BiFunction<Employee, Department, UserDepartmentDto> userDepartmentDTOBiFunction = (x1, x2) -> UserDepartmentDto.builder()
//            .age(x1.getAge())
//            .departmentId(x2.getId())
//            .departmentName(x2.getName())
//            .userName(x1.getName())
//            .userId(x1.getId())
//            .loc(x2.getLocation())
//            .salary(x1.getSalary()).build();

}