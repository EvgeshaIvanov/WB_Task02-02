package com.example.analysisofalgorithmsapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.analysisofalgorithmsapp.databinding.ActivityMainBinding
import java.lang.System.currentTimeMillis
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue
import kotlin.time.toDuration

class MainActivity : AppCompatActivity() {
    //инструкция в файле Readme
    private lateinit var binding: ActivityMainBinding

    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tutorialButton.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("Инструкция")
                .setItems(R.array.tutorials) { _, _ -> }
                .create()
            dialog.show()
        }
        binding.buttonSort.setOnClickListener {
            if (binding.choosingStructure.text.contains("Массив")) {
                with(binding.sortingSelection.text) {
                    when {
                        contains("Пузырьковая сортировка") -> binding.apply {
                            tvUnsorted.text = setValueToArray().joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text = (bubbleSortArray(setValueToArray())).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { setValueToArray().sorted() }
                            val timingFromArraySorted =
                                measureTimedValue { bubbleSortArray(setValueToArray()) }
                            tvTimeInfo.text =
                                timingFromArraySorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromArraySorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        contains("Шейкерная сортировка") -> binding.apply {
                            tvUnsorted.text = setValueToArray().joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text =
                                (cocktailSortArray(setValueToArray())).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { setValueToArray().sorted() }
                            val timingFromArraySorted =
                                measureTimedValue { cocktailSortArray(setValueToArray()) }
                            tvTimeInfo.text =
                                timingFromArraySorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromArraySorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        contains("Сортировка Шелла") -> binding.apply {
                            tvUnsorted.text = setValueToArray().joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text = (sortShell(setValueToArray())).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { setValueToArray().sorted() }
                            val timingFromArraySorted =
                                measureTimedValue { sortShell(setValueToArray()) }
                            tvTimeInfo.text =
                                timingFromArraySorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromArraySorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        contains("Сортировка вставками") -> binding.apply {
                            tvUnsorted.text = setValueToArray().joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text =
                                (insertionSortArray(setValueToArray())).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { setValueToArray().sorted() }
                            val timingFromArraySorted =
                                measureTimedValue { insertionSortArray(setValueToArray()) }
                            tvTimeInfo.text =
                                timingFromArraySorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromArraySorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        else -> {
                            Toast.makeText(this@MainActivity, "Еще не готово", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            else if (binding.choosingStructure.text.contains("Лист")) {
                with(binding.sortingSelection.text) {
                    when {
                        contains("Пузырьковая сортировка") -> binding.apply {
                            val list = setValueToList()
                            tvUnsorted.text = list.joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text = (bubbleSortList(list)).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { list.sorted() }
                            val timingFromListSorted =
                                measureTimedValue { bubbleSortList(list) }
                            tvTimeInfo.text =
                                timingFromListSorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromListSorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        contains("Шейкерная сортировка") -> binding.apply {
                            val list = setValueToList()
                            tvUnsorted.text = list.joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text = (cocktailSortList(list)).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { list.sorted() }
                            val timingFromListSorted =
                                measureTimedValue { cocktailSortList(list) }
                            tvTimeInfo.text =
                                timingFromListSorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromListSorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        contains("Сортировка Шелла") -> binding.apply {
                            val list = setValueToList()
                            tvUnsorted.text = list.joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text = (sortShellList(list)).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { list.sorted() }
                            val timingFromListSorted =
                                measureTimedValue { sortShellList(list) }
                            tvTimeInfo.text =
                                timingFromListSorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromListSorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        contains("Сортировка вставками") -> binding.apply {
                            val list = setValueToList()
                            tvUnsorted.text = list.joinToString(", ")
                            tvUnsorted.movementMethod = ScrollingMovementMethod()
                            tvSorted.text = (insertionSortList(list)).joinToString(", ")
                            tvSorted.movementMethod = ScrollingMovementMethod()
                            tvTime.visibility = View.VISIBLE
                            val timingFromSystemSorted =
                                measureTimedValue { list.sorted() }
                            val timingFromListSorted =
                                measureTimedValue { insertionSortList(list) }
                            tvTimeInfo.text =
                                timingFromListSorted.duration.toDouble(DurationUnit.MILLISECONDS)
                                    .toString()
                            tvDifference.visibility = View.VISIBLE
                            tvDifferenceInfo.visibility = View.VISIBLE
                            tvDifferenceInfo.text =
                                ((timingFromSystemSorted.duration.toDouble(DurationUnit.MILLISECONDS)) - (timingFromListSorted.duration.toDouble(
                                    DurationUnit.MILLISECONDS
                                ))).toString()
                        }
                        else -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Ничего не выбрано",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }

            }
            else Toast.makeText(this,"Ничего не выбрано", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        val sorting = resources.getStringArray(R.array.sorting)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, sorting)
        binding.sortingSelection.setAdapter(arrayAdapter)
        val structure = resources.getStringArray(R.array.structure)
        val structureAdapter = ArrayAdapter(this, R.layout.dropdown_item, structure)
        binding.choosingStructure.setAdapter(structureAdapter)
    }

    private fun setValueToArray(): IntArray {
        val sizeToArray = binding.editText.text.toString().toInt()
        return IntArray(sizeToArray) { Random.nextInt(1..999) }
    }

    private fun setValueToList(): MutableList<Int> {
        val sizeToList = binding.editText.text.toString().toInt()
        return MutableList(sizeToList) { Random.nextInt(1..999) }

    }

    private fun bubbleSortArray(arr: IntArray): IntArray {
        do {
            var count = 0
            for (i in 0 until arr.size - 1) {
                if (arr[i] > arr[i + 1]) {
                    arr[i] = arr[i + 1].also { arr[i + 1] = arr[i] }
                    count++
                }
            }
        } while (count > 0)
        return arr
    }

    private fun insertionSortArray(arr: IntArray): IntArray {
        for (i in 1 until arr.size) {
            val current = arr[i]
            var j = i - 1
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = current
        }
        return arr
    }

    private fun cocktailSortArray(arr: IntArray): IntArray {
        fun swap(i: Int, j: Int) {
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
        do {
            var swapped = false
            for (i in 0 until arr.size - 1)
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1)
                    swapped = true
                }
            if (!swapped) break
            swapped = false
            for (i in arr.size - 2 downTo 0)
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1)
                    swapped = true
                }
        } while (swapped)
        return arr
    }

    private fun sortShell(arr: IntArray): IntArray {
        val n = arr.size
        var gap = n / 2
        while (gap > 0) {
            var i = gap
            while (i < n) {
                val temp = arr[i]
                var j = i
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap]
                    j -= gap
                }
                arr[j] = temp
                i += 1
            }
            gap /= 2
        }
        return arr
    }


    private fun bubbleSortList(arr: MutableList<Int>): MutableList<Int> {
        var array = arrayOf<Int>()
        for (el in arr) {
            array += el
        }
        do {
            var count = 0
            for (i in 0 until array.size - 1) {
                if (array[i] > array[i + 1]) {
                    array[i] = array[i + 1].also { array[i + 1] = array[i] }
                    count++
                }
            }
        } while (count > 0)
        return array.toMutableList()
    }

    private fun insertionSortList(arr: MutableList<Int>): MutableList<Int> {
        var array = arrayOf<Int>()
        for (el in arr) {
            array += el
        }
        for (i in 1 until array.size) {
            val current = array[i]
            var j = i - 1
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = current
        }
        return array.toMutableList()
    }

    private fun sortShellList(arr: MutableList<Int>): MutableList<Int> {
        val n = arr.size
        var gap = n / 2
        while (gap > 0) {
            var i = gap
            while (i < n) {
                val temp = arr[i]
                var j = i
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap]
                    j -= gap
                }
                arr[j] = temp
                i += 1
            }
            gap /= 2
        }
        return arr
    }

    private fun cocktailSortList(arr: MutableList<Int>): MutableList<Int> {

        fun swap(i: Int, j: Int) {
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
        do {
            var swapped = false
            for (i in 0 until arr.size - 1)
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1)
                    swapped = true
                }
            if (!swapped) break
            swapped = false
            for (i in arr.size - 2 downTo 0)
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1)
                    swapped = true
                }
        } while (swapped)
        return arr
    }


}