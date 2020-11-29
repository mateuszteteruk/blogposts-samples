import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.ClassLoaderCreator
import android.view.View

class SavedState : View.BaseSavedState {

    constructor(source: Parcel?, loader: ClassLoader?) : super(source, loader)
    constructor(superState: Parcelable?) : super(superState)

    companion object {

        @JvmField
        val CREATOR: ClassLoaderCreator<SavedState> = object : ClassLoaderCreator<SavedState> {
            override fun createFromParcel(source: Parcel): SavedState = SavedState(source, null)

            override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)

            override fun createFromParcel(source: Parcel, loader: ClassLoader): SavedState =
                SavedState(source, loader)
        }
    }
}