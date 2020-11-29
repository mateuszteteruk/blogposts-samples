import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

class SavedState extends View.BaseSavedState {

    public SavedState(final Parcel source, final ClassLoader loader) {
        super(source, loader);
    }

    public SavedState(final Parcelable superState) {
        super(superState);
    }

    public static final ClassLoaderCreator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
        @Override
        public SavedState createFromParcel(final Parcel source) {
            return new SavedState(source, null);
        }

        @Override
        public SavedState[] newArray(final int size) {
            return new SavedState[size];
        }

        @Override
        public SavedState createFromParcel(final Parcel source, final ClassLoader loader) {
            return new SavedState(source, loader);
        }
    };
}
