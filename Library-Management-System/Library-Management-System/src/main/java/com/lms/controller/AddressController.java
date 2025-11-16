package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.service.IAddressService;
import com.lms.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
//specify common url for entire class
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	IAddressService addressService;
	
	
	@Operation(operationId="createAddress", summary="Adding Address", description="This rest end point is used to create an Address")
	
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Created and return Address",
		        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Address.class)))
		})

	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody AddressDto addressDto) {
	    return addressService.saveAddress(addressDto);
	}

	@Operation(operationId="get Address By id", summary="Get one address by id", description="This rest end point is used to fetch and return Address by id")
	
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Address found",
		        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Address.class))),
		    @ApiResponse(responseCode = "404", description = "Address not found")
		})

	
	
	@GetMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> findAddressById(@PathVariable int addressId){
	    return addressService.findAddressById(addressId);
	}

	
	@PutMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(int addressId,@Valid @RequestBody AddressDto addressDto){
		return addressService.updateAddress(addressId,addressDto);
	}
	
	@DeleteMapping("/{addressId}")
    public ResponseEntity<ResponseStructure<String>> deleteAddress(@PathVariable int addressId) {
        return addressService.deleteAddressById(addressId);
    }

   
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Address>>> findAllAddresses() {
        return addressService.findAllAddresses();
    }
}
