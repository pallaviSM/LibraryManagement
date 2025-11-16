package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.util.ResponseStructure;

import jakarta.validation.Valid;

public interface IAddressService {

    ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto);

	ResponseEntity<ResponseStructure<Address>> findAddressById(int addressId);

	ResponseEntity<ResponseStructure<Address>> updateAddress(int addressId, @Valid AddressDto addressDto);

	ResponseEntity<ResponseStructure<String>> deleteAddressById(int addressId);

	ResponseEntity<ResponseStructure<List<Address>>> findAllAddresses();

}
