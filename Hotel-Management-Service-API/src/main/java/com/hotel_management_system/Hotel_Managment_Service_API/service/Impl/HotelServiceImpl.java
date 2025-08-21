package com.hotel_management_system.Hotel_Managment_Service_API.service.Impl;

import com.hotel_management_system.Hotel_Managment_Service_API.dto.request.RequestHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseBranchDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.ResponseHotelDto;
import com.hotel_management_system.Hotel_Managment_Service_API.dto.response.paginate.HotelPaginateResponseDto;
import com.hotel_management_system.Hotel_Managment_Service_API.entity.BranchEntity;
import com.hotel_management_system.Hotel_Managment_Service_API.entity.HotelEntity;
import com.hotel_management_system.Hotel_Managment_Service_API.exception.EntryNotFoundException;
import com.hotel_management_system.Hotel_Managment_Service_API.repository.HotelRepository;
import com.hotel_management_system.Hotel_Managment_Service_API.service.HotelService;
import com.hotel_management_system.Hotel_Managment_Service_API.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void create(RequestHotelDto dto) {
        try {
            hotelRepository.save(toHotel(dto));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(RequestHotelDto dto, String hotelId) throws SQLException {
        HotelEntity selectedHotel = hotelRepository.findById(hotelId).orElseThrow(()->new EntryNotFoundException("Hotel Not Found!"));

        selectedHotel.setHotelName(dto.getHotelName());
        selectedHotel.setUpdatedAt(LocalDateTime.now());
        selectedHotel.setStarRating(dto.getStarRating());
        selectedHotel.setStartingFrom(dto.getStartingFrom());
        selectedHotel.setDescription(byteCodeHandler.StringToBlob(dto.getDescription()));

        hotelRepository.save(selectedHotel);
    }

    @Override
    public void delete(String hotelId) {
        hotelRepository.findById(hotelId).orElseThrow(()->new EntryNotFoundException("Hotel Not Found!"));
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public ResponseHotelDto findById(String hotelId) throws SQLException {
        HotelEntity searchHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new EntryNotFoundException("Hotel Not Found!"));

        return toResponseHotelDto(searchHotel);
    }

    @Override
    public HotelPaginateResponseDto findAll(int page, int size, String searchText) {
        return HotelPaginateResponseDto.builder()
                .dataCount(hotelRepository.countAllHotels(searchText))
                .dataList(
                        hotelRepository.searchHotels(searchText, PageRequest.of(page, size))
                                .stream().map(e-> {
                                    try {
                                        return toResponseHotelDto(e);
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }).collect(Collectors.toList())
                ).build();
    }


    //////////////////////////////       Dto  maps to Entity         ///////////////////////////////

    private HotelEntity toHotel(RequestHotelDto dto) throws SQLException {
        return dto == null ? null :
                HotelEntity.builder()
                        .hotelName(dto.getHotelName())
                        .hotelId(UUID.randomUUID().toString())
                        .starRating(dto.getStarRating())
                        .description(byteCodeHandler.StringToBlob(dto.getDescription()))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .activeStatus(true)
                        .startingFrom(dto.getStartingFrom())
                        .build();
    }

    //////////////////////////////       Entity  maps to Dto         ///////////////////////////////

    private ResponseHotelDto toResponseHotelDto(HotelEntity hotelEntity) throws SQLException {
        return hotelEntity == null ? null :
                ResponseHotelDto.builder()
                        .hotelName(hotelEntity.getHotelName())
                        .hotelId(UUID.randomUUID().toString())
                        .starRating(hotelEntity.getStarRating())
                        .description(byteCodeHandler.blobToString(hotelEntity.getDescription()))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .activeStatus(true)
                        .startingFrom(hotelEntity.getStartingFrom())
                        .branches(
                                hotelEntity.getBranches().stream().map(e-> {
                                    return toResponseBranchDto(e);
                                }).toList()
                        )
                        .build();
    }

    private ResponseBranchDto toResponseBranchDto(BranchEntity branch){
        return branch==null?null:
                ResponseBranchDto.builder()
                        .branchId(branch.getBranchId())
                        .branchName(branch.getBranchName())
                        .roomCount(branch.getRoomCount())
                        .branchType(branch.getBranchType())
                        .build();
    }
}
