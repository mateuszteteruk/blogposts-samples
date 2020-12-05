// ....

val safDelegate: StorageAccessFrameworkDelegate


const val REQ_CODE_EXPORT = 45123
const val REQ_CODE_IMPORT = 45124

// ...


override fun onCreate(savedInstanceState: Bundle?) {
    // .....

    // example for opening file
    startActivityForResult(safDelegate.createFilePickerIntent(), REQ_CODE_IMPORT)

    // ....
}



override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    when (requestCode) {
        REQ_CODE_EXPORT -> {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data?.data ?: return
                // handle uri
            }
        }
        REQ_CODE_IMPORT -> {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data?.data ?: return
                // handle uri
            }
        }

    }
    super.onActivityResult(requestCode, resultCode, data)
}