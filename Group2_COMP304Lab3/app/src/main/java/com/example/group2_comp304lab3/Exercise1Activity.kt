package com.example.group2_comp304lab3

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class Exercise1Activity : AppCompatActivity() {

    private lateinit var imgCanvas: ImageView
    private lateinit var tvCoordinates: TextView

    private lateinit var canvas: Canvas
    private lateinit var bitmap: Bitmap

    private var initX = 10f
    private var stepSize = 50f

    private var startx = 10f
    private var starty = 10f
    private var endx = 10f
    private var endy = 10f
    private val paint = Paint()

    init {
        paint.color = Color.RED
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise1)

        // get the views
        tvCoordinates = findViewById(R.id.tvCoordinates)
        imgCanvas = findViewById(R.id.imgCanvas)
        imgCanvas.isFocusable = true

        val upButton = findViewById<ImageButton>(R.id.btnUp)
        val downButton = findViewById<ImageButton>(R.id.btnDown)
        val leftButton = findViewById<ImageButton>(R.id.btnLeft)
        val rightButton = findViewById<ImageButton>(R.id.btnRight)
        val clearCanvasButton = findViewById<Button>(R.id.btnClearCanvas)
        val rgpColor = findViewById<RadioGroup>(R.id.rgpColor)
        val sprThickness = findViewById<Spinner>(R.id.sprThickness)

        // update x, y coordinates when the up/ down/ left/ right buttons were clicked
        upButton.setOnClickListener {
            endy -= stepSize
            drawLine(canvas)
        }
        downButton.setOnClickListener {
            endy += stepSize
            drawLine(canvas)
        }
        leftButton.setOnClickListener {
            endx -= stepSize
            drawLine(canvas)
        }
        rightButton.setOnClickListener {
            endx += stepSize
            drawLine(canvas)
        }
        clearCanvasButton.setOnClickListener {
            clearCanvas(canvas)
        }

        // change the bush color when user checked different radio buttons
        rgpColor.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rdoRed -> {
                    paint.color = Color.RED
                }
                R.id.rdoYellow -> {
                    paint.color = Color.YELLOW
                }
                R.id.rdoCyan -> {
                    paint.color = Color.CYAN
                }
            }
        }

        // update the bush's stroke width when user picked a thickness from the drop down list
        sprThickness.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "") {
                    paint.strokeWidth = selectedItem.toFloat()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        // initialise the bitmap based on the view's dimension
        imgCanvas.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Remove the listener to avoid multiple calls
                imgCanvas.viewTreeObserver.removeOnGlobalLayoutListener(this)

                // call drawToBitmap()
                bitmap =
                    Bitmap.createBitmap(imgCanvas.width, imgCanvas.height, Bitmap.Config.ARGB_8888)
                initX = (imgCanvas.width / 2).toFloat()
                startx = initX
                endx = initX
                canvas = Canvas(bitmap)
                imgCanvas.setImageBitmap(bitmap)
                imgCanvas.visibility = View.VISIBLE

            }
        })

        tvCoordinates.text = resources.getString(R.string.help)
    }

    // handle arrow keys events, update x, y coordinates accordingly and draw the line
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                imgCanvas.isFocusable = true
                imgCanvas.requestFocus()
                endy += stepSize
                drawLine(canvas)
                imgCanvas.invalidate()
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                imgCanvas.isFocusable = true
                imgCanvas.requestFocus()
                endx += stepSize
                drawLine(canvas)
                imgCanvas.invalidate()
                return true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                imgCanvas.isFocusable = true
                imgCanvas.requestFocus()
                endx -= stepSize
                drawLine(canvas)
                imgCanvas.invalidate()
                return true
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                imgCanvas.isFocusable = true
                imgCanvas.requestFocus()
                endy -= stepSize
                drawLine(canvas)
                imgCanvas.invalidate()
                return true
            }
        }
        return false
    }

    // function to draw the line based on x, y coordinates
    @SuppressLint("SetTextI18n")
    private fun drawLine(canvas: Canvas) {
        tvCoordinates.text = "y = $endy"

        canvas.drawLine(startx, starty, endx, endy, paint)
        startx = endx
        starty = endy
    }

    // clear the canvas and reset all the parameters
    private fun clearCanvas(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        startx = initX
        starty = 10f
        endx = initX
        endy = 10f
        tvCoordinates.text = resources.getString(R.string.help)
    }

}