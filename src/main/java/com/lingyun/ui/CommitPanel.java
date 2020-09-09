package com.lingyun.ui;

import com.intellij.openapi.project.Project;
import com.lingyun.model.DataSettings;
import com.lingyun.model.TypeAlias;
import com.lingyun.storage.GitCommitMessageHelperSettings;
import git4idea.branch.GitBranchUtil;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.util.List;


public class CommitPanel {
    private JPanel mainPanel;
    private JComboBox changeType;
    private JTextField changeScope;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JTextField closedIssues;
    private JTextArea breakingChanges;

    public CommitPanel(Project project, GitCommitMessageHelperSettings settings) {
        // 插件配置信息
        DataSettings dateSettings = settings.getDateSettings();

        // 加载插件自定义配置Type
        List<TypeAlias> typeAliases = dateSettings.getTypeAliases();
        for (TypeAlias type : typeAliases) {
            changeType.addItem(type);
        }

        // 获取当前分支名称,并获取需求名称
        String currentBranchName = GitBranchUtil.getCurrentRepository(project).getCurrentBranch().getName();
        if (StringUtils.isNotBlank(currentBranchName)) {
            String[] s = currentBranchName.split("_");
            if (s.length > 0) {
                String requirementName = s[s.length - 1];
                changeScope.setText(requirementName);
            }
        }

    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    CommitMessage getCommitMessage(GitCommitMessageHelperSettings settings) {
        return new CommitMessage(
                settings,
                (TypeAlias) changeType.getSelectedItem(),
                (String) changeScope.getText().trim(),
                shortDescription.getText().trim(),
                longDescription.getText().trim(),
                closedIssues.getText().trim(),
                breakingChanges.getText().trim()
        );
    }

}
