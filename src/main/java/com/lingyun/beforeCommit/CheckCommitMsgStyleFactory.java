package com.lingyun.beforeCommit;

import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.changes.CommitContext;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.checkin.CheckinHandlerFactory;
import org.jetbrains.annotations.NotNull;

public class CheckCommitMsgStyleFactory extends CheckinHandlerFactory {

    @NotNull
    @Override
    public CheckinHandler createHandler(@NotNull CheckinProjectPanel checkinProjectPanel,
                                        @NotNull CommitContext commitContext) {
        return new CheckCommitMsgStyleHandler(checkinProjectPanel.getProject(), checkinProjectPanel);
    }
}
