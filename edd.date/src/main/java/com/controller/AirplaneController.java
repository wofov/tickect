package com.air_force.controller;

import com.air_force.domain.AirlineDTO;
import com.air_force.domain.AirplaneDTO;
import com.air_force.domain.SeatByCityDTO;
import com.air_force.domain.SeatInfoDTO;
import com.air_force.entity.Airplane;
import com.air_force.service.AirplaneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "비행기 관리")
@RestController
@RequestMapping("/api")
public class AirplaneController {

    private final AirplaneService airplaneService;
    public AirplaneController(AirplaneService airplaneService){
        this.airplaneService = airplaneService;
    }

    @ApiOperation(value = "비행기 예매 정보 넣기")
    @PostMapping("/airplanes")
    public ResponseEntity<String> getAirplaneCreate(@RequestBody AirplaneDTO dto){
        return ResponseEntity.ok(airplaneService.AirplaneCreate(dto));
    }

    @ApiOperation(value = "항공사 목록")
    @GetMapping("/airlines")
    public ResponseEntity<List<String>> getAirlineRead(){
        return ResponseEntity.ok(airplaneService.AirlineRead());
    }

    @ApiOperation(value = "항공사 지정 후 운행 Month 목록")
    @GetMapping("/{airline}")
    public ResponseEntity<List<Airplane>> getAirlineRaceMonthRead(
            @ApiParam(value = "항공사를 넣어주세요",required = true) @PathVariable String airline){
        return ResponseEntity.ok(airplaneService.AirLineRaceMonthRead(airline));
    }

    @ApiOperation(value = "예매 가능 비행기 조회 항공사 & Month")
    @GetMapping("/{airline}/{month}")
    public ResponseEntity<AirlineDTO> getAirplaneRead(
            @ApiParam(value = "항공사를 넣어주세요",required = true) @PathVariable String airline,
            @ApiParam(value = "운행 Month를 넣어주세요",required = true) @PathVariable String month){
        return ResponseEntity.ok(airplaneService.AirplaneRead(airline,month));
    }

    @ApiOperation(value = "좌석 정보")
    @GetMapping("/{airline}/{month}/{date}")
    public ResponseEntity<SeatInfoDTO> getSeatInfo(
            @ApiParam(value = "항공사",required = true) @PathVariable String airline,
            @ApiParam(value = "운행 Month",required = true) @PathVariable String month,
            @ApiParam(value = "운행 Date",required = true) @PathVariable String date){
        return ResponseEntity.ok(airplaneService.SeatInfo(airline,month,date));
    }
    @ApiOperation(value = "출발국 목록")
    @GetMapping("/{airline}/depart-city")
    public ResponseEntity<List<String>> getDepartCity(
            @ApiParam(value = "항공사",required = true) @PathVariable String airline){
        return ResponseEntity.ok(airplaneService.DepartCity(airline));
    }
    @ApiOperation(value = "출발국에 따른 도착국 목록")
    @GetMapping("/{airline}/{departCity}/arrive-city")
    public ResponseEntity<List<String>> getArriveCity(
            @ApiParam(value = "항공사",required = true) @PathVariable String airline,
            @ApiParam(value = "출발국",required = true) @PathVariable String departCity){
        return ResponseEntity.ok(airplaneService.ArriveCity(airline,departCity));
    }
    @ApiOperation(value = "상세 검색 - 출발,도착 검색, 운행 가능 시간 확인")
    @GetMapping("/{airline}/{departCity}/{arriveCity}/month")
    public ResponseEntity<List<SeatByCityDTO>> getRaceMonthAndDate(
            @ApiParam(value = "항공사",required = true) @PathVariable String airline,
            @ApiParam(value = "출발국",required = true) @PathVariable String departCity,
            @ApiParam(value = "도착국",required = true) @PathVariable String arriveCity){
        return ResponseEntity.ok(airplaneService.RaceMonthAndDate(airline,departCity,arriveCity));
    }


}
