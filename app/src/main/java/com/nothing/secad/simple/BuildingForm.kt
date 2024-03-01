package com.nothing.secad.simple

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.nothing.secad.R
import com.nothing.secad.databinding.ActivityBuildingFormBinding
import com.skydoves.powerspinner.PowerSpinnerView

class BuildingForm : AppCompatActivity() {
    private lateinit var binding: ActivityBuildingFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent.getStringExtra("check").toString() == "abc"){
            binding.hideElevator.visibility = View.GONE
            binding.hideWaterTank.visibility = View.GONE
        }

        setupPowerSpinner(binding.noOfParkingBuilding)
        setupPowerSpinner(binding.noOfWatchmanBuilding)
        setupPowerSpinner(binding.noOfGardenBuilding)
        setupPowerSpinner(binding.noOfTempleBuilding)
        setupPowerSpinner(binding.noOfWaterTankBuilding)
        setupPowerSpinner(binding.noOfTotalHomeBuilding)
        setupPowerSpinner(binding.noOfElevatorBuilding)

        binding.btnBuildingSignUp.setOnClickListener {
            val selectedParking = getSelectedItem(binding.noOfParkingBuilding)
            val selectedElevator = getSelectedItem(binding.noOfElevatorBuilding)
            val selectedWatchman = getSelectedItem(binding.noOfWatchmanBuilding)
            val selectedGarden = getSelectedItem(binding.noOfGardenBuilding)
            val selectedTemple = getSelectedItem(binding.noOfTempleBuilding)
            val selectedWaterTank = getSelectedItem(binding.noOfWaterTankBuilding)
            val selectedTotalHome = getSelectedItem(binding.noOfTotalHomeBuilding)


            if(intent.getStringExtra("check").toString() == "abc"){

                if (selectedParking  == "--select no of--" || selectedWatchman  == "--select no of--" || selectedGarden  == "--select no of--"
                    || selectedTemple  == "--select no of--" || selectedTotalHome  == "--select no of--") {
                    showToast("Please fill all field")
                    // You can also prevent further actions here or show an error message
                } else {
                    // Valid item selected, you can proceed with your logic here
                    val intent = Intent(this, SignupActivity::class.java).apply {
                        putExtra("selectedParking", selectedParking)
                        putExtra("selectedWatchman", selectedWatchman)
                        putExtra("selectedGarden", selectedGarden)
                        putExtra("selectedTemple", selectedTemple)
                        putExtra("selectedTotalHome", selectedTotalHome)
                    }
                    startActivity(intent)
                }
            }else{
                if (selectedParking  == "--select no of--" || selectedElevator  == "--select no of--" || selectedWatchman  == "--select no of--"
                    || selectedGarden  == "--select no of--" || selectedTemple  == "--select no of--" || selectedWaterTank  == "--select no of--"
                    || selectedTotalHome  == "--select no of--") {
                    showToast("Please fill all field")
                    // You can also prevent further actions here or show an error message
                } else {
                    // Valid item selected, you can proceed with your logic here
                    val intent = Intent(this, SignupActivity::class.java).apply {
                        putExtra("selectedParking", selectedParking)
                        putExtra("selectedElevator", selectedElevator)
                        putExtra("selectedWatchman", selectedWatchman)
                        putExtra("selectedGarden", selectedGarden)
                        putExtra("selectedTemple", selectedTemple)
                        putExtra("selectedWaterTank", selectedWaterTank)
                        putExtra("selectedTotalHome", selectedTotalHome)
                    }

                    startActivity(intent)
                }
            }
        }
    }

    private fun setupPowerSpinner(spinner: PowerSpinnerView) {
        val itemsArray = resources.getStringArray(R.array.spinner_form)
        val items = itemsArray.toList()

        spinner.setItems(items)
        spinner.selectItemByIndex(0)

        spinner.setOnSpinnerItemSelectedListener<String> { _, _, _, item ->
//            showToast("Selected item: $item")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@BuildingForm, message, Toast.LENGTH_LONG).show()
    }

    private fun getSelectedItem(spinner: PowerSpinnerView): String {
        val selectedIndex = spinner.selectedIndex
        val itemsArray = resources.getStringArray(R.array.spinner_form)
        return itemsArray.getOrNull(selectedIndex) ?: ""
    }
}
