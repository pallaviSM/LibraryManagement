package com.lms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.util.ResponseStructure;

@Service
public class AddressService implements IAddressService{
	
@Autowired
AddressRepository addressRepository;

@Autowired
ModelMapper modelMapper;
public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto)
{
//	Address address=new Address();
//	address.setHouseNumber(addressDto.getHouseNumber());
//	address.setArea(addressDto.getArea());
//	address.setCity(addressDto.getCity());
//	address.setCountry(addressDto.getCountry());
//	address.setPincode(addressDto.getPincode());
//	address.setState(addressDto.getState());
	
	
	Address address=modelMapper.map(addressDto, Address.class);
	addressRepository.save(address);
	
	
	
	ResponseStructure<Address> responseStructure=new ResponseStructure<>();
	responseStructure.setMessage("Address Saved Succesfully...");
	responseStructure.setStatusCode(HttpStatus.CREATED.value());
	responseStructure.setData(address);
	return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.CREATED);
}
public ResponseEntity<ResponseStructure<Address>> findAddressById(int addressId){
	Optional<Address> optional=addressRepository.findById(addressId);
	if(optional.isPresent()) {
		Address address=optional.get();
		ResponseStructure<Address> responseStructure=new ResponseStructure<>();
		responseStructure.setMessage("Addresss Found Successfully For This id..");
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setData(address);
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.FOUND);
		
	}
	else
	{
		throw new AddressIdNotFoundException("Address Not Update..");
	}
}

public ResponseEntity<ResponseStructure<Address>> updateAddress(int addressId,AddressDto addressDto){
	Optional <Address> optional=addressRepository.findById(addressId);
	Address address=modelMapper.map(addressDto, Address.class);
	if(optional.isPresent()) {
		addressRepository.save(address);
		ResponseStructure<Address> responseStructure=new ResponseStructure<>();
		responseStructure.setMessage("Addresss Update Successfully For This id..");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setData(address);
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK);
	}
	else
	{
		throw new AddressIdNotFoundException("Address Not Update..");
	}
}

public ResponseEntity<ResponseStructure<String>> deleteAddressById(int addressId) {
    Optional<Address> optional = addressRepository.findById(addressId);
    ResponseStructure<String> responseStructure = new ResponseStructure<>();

    if (optional.isPresent()) {
        addressRepository.deleteById(addressId);
        responseStructure.setMessage("Address Deleted Successfully.");
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setData("Deleted Address ID: " + addressId);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    } else {
    	throw new AddressIdNotFoundException("Address Not Update..");
    }
}



public ResponseEntity<ResponseStructure<List<Address>>> findAllAddresses() {
    List<Address> addresses = addressRepository.findAll();
    ResponseStructure<List<Address>> responseStructure = new ResponseStructure<>();

    if (addresses.isEmpty()) {
    	throw new AddressIdNotFoundException("Address Not Update..");
    } else {
        responseStructure.setMessage("All Addresses Retrieved Successfully.");
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setData(addresses);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
}
}