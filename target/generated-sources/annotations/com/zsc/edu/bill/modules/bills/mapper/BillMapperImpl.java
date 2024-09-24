package com.zsc.edu.bill.modules.bills.mapper;

import com.zsc.edu.bill.modules.bills.dto.BillDto;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-20T14:48:01+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class BillMapperImpl implements BillMapper {

    @Override
    public BillDto toDto(Bill entity) {
        if ( entity == null ) {
            return null;
        }

        BillDto billDto = new BillDto();

        billDto.setTitle( entity.getTitle() );
        billDto.setBody( entity.getBody() );
        billDto.setMoney( entity.getMoney() );
        if ( entity.getType() != null ) {
            billDto.setType( entity.getType().name() );
        }
        billDto.setContactEmail( entity.getContactEmail() );
        billDto.setCompanyName( entity.getCompanyName() );

        return billDto;
    }

    @Override
    public Bill toEntity(BillDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setTitle( dto.getTitle() );
        bill.setBody( dto.getBody() );
        bill.setMoney( dto.getMoney() );
        if ( dto.getType() != null ) {
            bill.setType( Enum.valueOf( Bill.billType.class, dto.getType() ) );
        }
        bill.setContactEmail( dto.getContactEmail() );
        bill.setCompanyName( dto.getCompanyName() );

        return bill;
    }

    @Override
    public List<BillDto> toDto(List<Bill> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillDto> list = new ArrayList<BillDto>( entityList.size() );
        for ( Bill bill : entityList ) {
            list.add( toDto( bill ) );
        }

        return list;
    }

    @Override
    public List<Bill> toEntity(List<BillDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Bill> list = new ArrayList<Bill>( dtoList.size() );
        for ( BillDto billDto : dtoList ) {
            list.add( toEntity( billDto ) );
        }

        return list;
    }

    @Override
    public void convert(BillDto dto, Bill entity) {
        if ( dto == null ) {
            return;
        }

        entity.setTitle( dto.getTitle() );
        entity.setBody( dto.getBody() );
        entity.setMoney( dto.getMoney() );
        if ( dto.getType() != null ) {
            entity.setType( Enum.valueOf( Bill.billType.class, dto.getType() ) );
        }
        else {
            entity.setType( null );
        }
        entity.setContactEmail( dto.getContactEmail() );
        entity.setCompanyName( dto.getCompanyName() );
    }
}
