/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package cardstream;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import Logger.Log;

public class StreamRetentionFragment extends Fragment {

    CardStreamState mState;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        Log.i("우와","쿠앙");
    }

    public void storeCardStream(CardStreamState state) {
        mState = state;
    }

    public CardStreamState getCardStream() {
        return mState;
    }
}
