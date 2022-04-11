package com.univer.carshowroom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
import com.univer.carshowroom.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val listCars = Car().getCars()
    private var filterCar = FilterCar()

    private val adapterResult: CarsResultAdapter by lazy {
        CarsResultAdapter()
    }

    private val adapterBrand: CarsShowroomAdapter by lazy {
        CarsShowroomAdapter { text, isClicked ->
            filterCar.brand = if (isClicked) text else ""
            update()
        }
    }

    private val adapterHp: CarsShowroomAdapter by lazy {
        CarsShowroomAdapter { text, isClicked ->
            filterCar.hp = if (isClicked) text else ""
            update()
        }
    }

    private val adapterSalon: CarsShowroomAdapter by lazy {
        CarsShowroomAdapter { text, isClicked ->
            filterCar.salon = if (isClicked) text else ""
            update()
        }
    }

    private val adapterTransmission: CarsShowroomAdapter by lazy {
        CarsShowroomAdapter { text, isClicked ->
            filterCar.transmission = if (isClicked) text else ""
            update()
        }
    }

    private val adapterAccident: CarsShowroomAdapter by lazy {
        CarsShowroomAdapter { text, isClicked ->
            filterCar.accident = if (isClicked) text else ""
            update()
        }
    }

    private val adapterPrice: CarsShowroomAdapter by lazy {
        CarsShowroomAdapter { text, isClicked ->
            filterCar.price = if (isClicked) text else ""
            update()
        }
    }

    private val adapterYear: CarsShowroomAdapter by lazy {
        CarsShowroomAdapter { text, isClicked ->
            filterCar.year = if (isClicked) text else ""
            update()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerBrand.adapter = adapterBrand
        binding.recyclerHp.adapter = adapterHp
        binding.recyclerSalon.adapter = adapterSalon
        binding.recyclerTransmission.adapter = adapterTransmission
        binding.recyclerAccident.adapter = adapterAccident
        binding.recyclerPrice.adapter = adapterPrice
        binding.recyclerYear.adapter = adapterYear
        binding.recyclerResult.adapter = adapterResult

        binding.recyclerBrand.setNoAnimation()
        binding.recyclerHp.setNoAnimation()
        binding.recyclerSalon.setNoAnimation()
        binding.recyclerTransmission.setNoAnimation()
        binding.recyclerAccident.setNoAnimation()
        binding.recyclerPrice.setNoAnimation()
        binding.recyclerYear.setNoAnimation()
        binding.recyclerResult.setNoAnimation()

        setAdapter(listCars)
    }

    private fun setAdapter(listCars: List<Car>) {
        val listBrand = mutableListOf<AdapterEntity>()
        val listSalon = mutableListOf<AdapterEntity>()
        val listTransmission = mutableListOf<AdapterEntity>()
        val listAccident = mutableListOf<AdapterEntity>()

        val listHp = mutableListOf<AdapterEntity>()
        val rangeHpList = mutableListOf(1..100, 101..200, 201..300, 301..500, 501..1000)
        val listPrice = mutableListOf<AdapterEntity>()
        val rangePrice =
            mutableListOf(1..1000, 1001..5000, 5001..10000, 10001..50000, 50001..100000)
        val listYear = mutableListOf<AdapterEntity>()
        val rangeYearList =
            mutableListOf(1990..1999, 2000..2009, 2010..2015, 2016..2022)

        Log.d("listCars", listCars.toString() + "filter = " + filterCar)

        listCars.forEach { car ->
            if (listBrand.find { it.text == car.brand } == null)
                listBrand.add(AdapterEntity(car.brand, filterCar.brand == car.brand))
            if (listTransmission.find { it.text == car.transmission } == null)
                listTransmission.add(
                    AdapterEntity(
                        car.transmission,
                        filterCar.transmission == car.transmission
                    )
                )
            if (listSalon.find { it.text == car.salon } == null)
                listSalon.add(AdapterEntity(car.salon, filterCar.salon == car.salon))
            if (listAccident.find { it.text == car.accident.toString() } == null)
                listAccident.add(
                    AdapterEntity(
                        car.accident.toString(),
                        filterCar.accident == car.accident.toString()
                    )
                )
        }

        listCars.forEach { car ->
            rangePrice.forEach { intRange ->
                if (listPrice.find { it.text == intRange.toString() } == null)
                    if (intRange.contains(car.price)) {
                        listPrice.add(
                            AdapterEntity(
                                intRange.toString(),
                                if (filterCar.price != "") filterCar.price.toRange() == intRange else false
                            )
                        )
                    }
            }
        }
        listCars.forEach { car ->
            rangeHpList.forEach { intRange ->
                if (listHp.find { it.text == intRange.toString() } == null)
                    if (intRange.contains(car.hp)) {
                        listHp.add(
                            AdapterEntity(
                                intRange.toString(),
                                if (filterCar.hp != "") filterCar.hp.toRange() == intRange else false
                            )
                        )
                    }
            }
        }
        listCars.forEach { car ->
            rangeYearList.forEach { intRange ->
                if (listYear.find { it.text == intRange.toString() } == null)
                    if (intRange.contains(car.year)) {
                        listYear.add(
                            AdapterEntity(
                                intRange.toString(),
                                if (filterCar.year != "") filterCar.year.toRange() == intRange else false
                            )
                        )
                    }
            }
        }


        adapterBrand.submitList(listBrand)
        adapterHp.submitList(listHp)
        adapterSalon.submitList(listSalon)
        adapterTransmission.submitList(listTransmission)
        adapterAccident.submitList(listAccident)
        adapterPrice.submitList(listPrice)
        adapterYear.submitList(listYear)
    }

    private fun update() {
        var newList = listCars
        if (filterCar.brand != "") {
            newList = newList.filter { it.brand == filterCar.brand }
        }
        if (filterCar.salon != "") {
            newList = newList.filter { it.salon == filterCar.salon }
        }
        if (filterCar.transmission != "") {
            newList = newList.filter { it.transmission == filterCar.transmission }
        }
        if (filterCar.accident != "") {
            newList = newList.filter { it.accident.toString() == filterCar.accident }
        }
        if (filterCar.price != "") {
            newList = newList.filter {
                filterCar.price.toRange().contains(it.price)
            }
        }
        if (filterCar.hp != "") {
            newList = newList.filter {
                filterCar.hp.toRange().contains(it.hp)
            }
        }
        if (filterCar.year != "") {
            newList = newList.filter {
                filterCar.year.toRange().contains(it.year)
            }
        }

        Log.d("newList", newList.toString() + "filter = " + filterCar)
        adapterResult.submitList(newList)

        setAdapter(newList)
    }

    private fun String.toRange(): IntRange = this
        .split("..")
        .let { (a, b) -> a.toInt()..b.toInt() }

    private fun RecyclerView.setNoAnimation() {
        val animator: ItemAnimator? = itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }
}