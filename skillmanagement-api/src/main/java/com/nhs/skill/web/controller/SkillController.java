package com.nhs.skill.web.controller;

import com.nhs.skill.data.domain.Employee;
import com.nhs.skill.data.domain.Skill;
import com.nhs.skill.data.dto.skill.SkillUpdateRequestDTO;
import com.nhs.skill.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "Skill Management", description = "REST Apis related to Skill Entity")
@RestController
@RequestMapping("/nhs/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @ApiOperation(value = "Get list of Skills"
            , response = Iterable.class, tags = "getAllSkills")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @GetMapping("/getAllSkills")
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();

        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @ApiOperation(value = "Get list of Skills by EmployeeId"
            , response = Iterable.class, tags = "getAllSkillsByEmployeeId")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "employeeId",
                    value = "EmployeeId of the employee",
                    required = true,
                    dataType = "Long",
                    paramType = "path"
            )
    })
    @GetMapping("/getAllSkillsByEmployeeId/{employeeId}")
    public ResponseEntity<List<Skill>> getAllSkillsByEmployeeId(@PathVariable(value = "employeeId") Long employeeId) {

        List<Skill> skills = skillService.getAllSkillsByEmployeeId(employeeId);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @ApiOperation(value = "Get list of Skills by Skill Id"
            , response = Skill.class, tags = "getSkillsById")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "Skill Id",
                    required = true,
                    dataType = "Long",
                    paramType = "path"
            )
    })
    @GetMapping("/getSkillsById/{id}")
    public ResponseEntity<Skill> getSkillsById(@PathVariable(value = "id") Long id) {
        Skill skill = skillService.getSkillsById(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @ApiOperation(value = "Get list of Employees by Skill Id"
            , response = Iterable.class, tags = "getAllEmployeesBySkillId")
    @GetMapping("/getAllEmployeesBySkillId/{skillId}")
    public ResponseEntity<List<Employee>> getAllEmployeesBySkillId(@PathVariable(value = "skillId") Long skillId) {
        List<Employee> employees = skillService.getAllEmployeesBySkillId(skillId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @ApiOperation(value = "Add Skill details by Employee Id"
            , response = Skill.class, tags = "addSkillByEmployeeId")
    @PostMapping("/addSkillByEmployeeId/{employeeId}")
    public ResponseEntity<Skill> addSkillByEmployeeId(@PathVariable(value = "employeeId") Long employeeId, @RequestBody Skill skillRequest) {
        Skill skill = skillService.addSkill(employeeId, skillRequest);
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Skill details by Skill Id"
            , response = Skill.class, tags = "updateSkill")
    @PutMapping("/updateSkill/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable("id") long id, @RequestBody SkillUpdateRequestDTO skillRequest) {
        Skill skill = skillService.updateSkill(id, skillRequest);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @ApiOperation(value = "delete Skill details based om EmployeeId and Skill Id"
            , tags = "deleteSkillFromEmployee")
    @DeleteMapping("/deleteSkillFromEmployee/{employeeId}/skills/{skillId}")
    public ResponseEntity<HttpStatus> deleteSkillFromEmployee(@PathVariable(value = "employeeId") Long employeeId
            , @PathVariable(value = "skillId") Long skillId) {
        skillService.deleteSkillFromEmployee(employeeId, skillId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "delete Skill details based on Skill Id"
            , tags = "deleteSkill")
    @DeleteMapping("/deleteSkill/{id}")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable("id") long id) {
        skillService.deleteSkill(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
