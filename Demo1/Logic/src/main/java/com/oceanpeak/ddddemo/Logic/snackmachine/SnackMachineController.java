package com.oceanpeak.ddddemo.Logic.snackmachine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oceanpeak.ddddemo.Logic.sharedkernel.Money;

@Controller
@RequestMapping(path = "/snackmachines")
public class SnackMachineController {

	@Autowired
    SnackMachineRepository snackMachineRepository;
	static SnackMachine snackMachine = new SnackMachine(); // We will change this when we
	// introduce persistence for our domain model.

	@GetMapping()
    @ResponseBody
    public List<SnackMachineDto> getSnackMachines() {
        List<SnackMachineDto> list = new ArrayList<>();
        snackMachineRepository.findAll().forEach(list::add);
        return list;
    }
	
	
	@GetMapping("/{id}")
	@ResponseBody
	public SnackMachineDto getSnackMachine(@PathVariable("id") long id) {
		return snackMachineRepository.findById(id).orElse(null);
	}

	@PutMapping("/{id}/moneyInTransaction/{coinOrNote}")
	public void inserMoney(@PathVariable("id") long id, @PathVariable("coinOrNote") String coinOrNote) {

		if (coinOrNote.equalsIgnoreCase("Cent"))
			snackMachine.insertMoney(Money.CENT);

		else if (coinOrNote.equalsIgnoreCase("TenCent"))
			snackMachine.insertMoney(Money.TENCENT);

		else if (coinOrNote.equalsIgnoreCase("Quarter"))
			snackMachine.insertMoney(Money.QUARTER);

		else if (coinOrNote.equalsIgnoreCase("Dollar"))
			snackMachine.insertMoney(Money.DOLLAR);

		else if (coinOrNote.equalsIgnoreCase("FiveDollar"))
			snackMachine.insertMoney(Money.FIVEDOLLAR);

		else if (coinOrNote.equalsIgnoreCase("TwentyDollar"))
			snackMachine.insertMoney(Money.TWENTYDOLLAR);
		
		snackMachineRepository.save(snackMachine.convertToSnackMachineDto());

	}
	
	@PutMapping("/{id}/moneyInTransaction")
	public void returnMoney(@PathVariable("id") long id) {
		SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
        snackMachine.returnMoney();
        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
	}
	
	@PutMapping("/{id}/{slotNumber}")

    public void buySnack(@PathVariable("id") long id, @PathVariable("slotNumber") int slotNumber) {

            SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
            SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
            snackMachine.buySnack(slotNumber);
            snackMachineRepository.save(snackMachine.convertToSnackMachineDto());

	}

}
	
	
	
	
	

