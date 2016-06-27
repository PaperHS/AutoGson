import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;

/**
 * Created by peggy on 16/6/26.
 */
public class AutoGsonGen extends BaseGenerateAction {
    protected PsiClass mClass;
    private PsiElementFactory mFactory;
    private Project mProject;

    public AutoGsonGen() {
        super(null);
    }

    public AutoGsonGen(CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        mProject = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        PsiFile mFile = PsiUtilBase.getPsiFileInEditor(editor, mProject);
        mClass = getTargetClass(editor, mFile);
        JsonUtilsDialog jsonD = new JsonUtilsDialog(mClass, mFactory, mFile, mProject);
        jsonD.setClass(mClass);
        jsonD.setFactory(mFactory);
        jsonD.setFile(mFile);
        jsonD.setProject(mProject);
        jsonD.setSize(600, 400);
        jsonD.setLocationRelativeTo(null);
        jsonD.setVisible(true);
    }
}
