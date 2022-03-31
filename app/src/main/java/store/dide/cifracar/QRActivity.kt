package store.dide.cifracar

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.interfaces.Detector

class QRActivity : AppCompatActivity() {

    private lateinit var svBarcode: SurfaceView

    private lateinit var detector: BarcodeDetector
    private lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qractivity)
        val actionBar = supportActionBar
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true)
            }

            val builder = AlertDialog.Builder(this)
            var taskHandler = Handler()
            var runnable = object:Runnable{
                override fun run() {
                    cameraSource.stop()
                    val alert = builder.create()
                    alert.show()
                    taskHandler.removeCallbacksAndMessages(null)
                }
            }

            svBarcode = findViewById(R.id.sv_barcode)

            detector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()

        detector.setProcessor(object : com.google.android.gms.vision.Detector.Processor<com.google.android.gms.vision.barcode.Barcode> {
            override fun release() {}
            @SuppressLint("MissingPermission")
            override fun receiveDetections(p0: com.google.android.gms.vision.Detector.Detections<com.google.android.gms.vision.barcode.Barcode>?) {
                val barcodes = p0?.detectedItems
                if (barcodes!!.size() > 0) {
                    if (barcodes.valueAt(0).displayValue.contains("cifracar.online")) {
                        builder.setMessage(barcodes.valueAt(0).displayValue)
                        builder.setPositiveButton("Закрыть") { dialog, which ->
                            startActivity(Intent(this@QRActivity, MainActivity::class.java))
                            cameraSource.start(svBarcode.holder)
                            finish()
                        }
                        taskHandler.post(runnable)
                    } else {
                        builder.setMessage("Вы используете некорректную карту")
                        builder.setPositiveButton("Закрыть") { dialog, which ->
                            cameraSource.start(svBarcode.holder)
                            finish()
                        }
                        taskHandler.post(runnable)
                    }
                }
//                } else {
//                    builder.setMessage("Ошибка сканирования")
//                    builder.setPositiveButton("Закрыть") { dialog, which ->
//                        cameraSource.start(svBarcode.holder)
//                        finish()
//                    }
//                    taskHandler.post(runnable)

            }
        })

            cameraSource = CameraSource.Builder(this, detector).setRequestedPreviewSize(1024, 768)
                .setRequestedFps(30f).setAutoFocusEnabled(true).build()
            svBarcode.holder.addCallback(object : SurfaceHolder.Callback2 {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    if (ContextCompat.checkSelfPermission(this@QRActivity, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                        cameraSource.start(svBarcode.holder)
                    else ActivityCompat.requestPermissions(this@QRActivity, arrayOf(android.Manifest.permission.CAMERA), 123)
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder,
                    format: Int,
                    width: Int,
                    height: Int
                ) {

                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    cameraSource.stop()
                }

                override fun surfaceRedrawNeeded(holder: SurfaceHolder) {

                }

            })
        }

        override fun onSupportNavigateUp(): Boolean {
            super.onSupportNavigateUp()
            onBackPressed()
            return true
        }

        @SuppressLint("MissingPermission")
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 123) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    cameraSource.start(svBarcode.holder)
                else Toast.makeText(this, "scanner", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            detector.release()
            cameraSource.stop()
            cameraSource.release()
        }
    }
