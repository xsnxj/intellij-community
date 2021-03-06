/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.java.propertyBased;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.Segment;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;

/**
 * @author peter
 */
abstract class ActionOnRange implements MadTestingAction {
  protected final RangeMarker myMarker;
  private TextRange finalRange;

  ActionOnRange(Document document, int start, int end) {
    myMarker = document.createRangeMarker(start, end);
  }

  int getStartOffset() {
    TextRange range = getFinalRange();
    return range == null ? -1 : range.getStartOffset();
  }

  Segment getCurrentRange() {
    return finalRange == null ? myMarker : finalRange;
  }

  @Nullable
  TextRange getFinalRange() {
    if (finalRange == null) {
      finalRange = myMarker.isValid() ? new TextRange(myMarker.getStartOffset(), myMarker.getEndOffset()) : null;
    }
    return finalRange;
  }
}
