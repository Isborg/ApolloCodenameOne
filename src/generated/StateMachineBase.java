/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm("GUI 1", null);
        } else {
            Form f = (Form)createContainer(resPath, "GUI 1");
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "GUI 1");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Button findButton(Component root) {
        return (com.codename1.ui.Button)findByName("Button", root);
    }

    public com.codename1.ui.Button findButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("Button", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("Button", aboutToShowThisContainer);
        }
        return cmp;
    }

    public static final int COMMAND_GUI1Bbbbb = 3;
    public static final int COMMAND_GUI1ASas = 2;

    protected boolean onGUI1Bbbbb() {
        return false;
    }

    protected boolean onGUI1ASas() {
        return false;
    }

    protected void processCommand(ActionEvent ev, Command cmd) {
        switch(cmd.getId()) {
            case COMMAND_GUI1Bbbbb:
                if(onGUI1Bbbbb()) {
                    ev.consume();
                    return;
                }
                break;

            case COMMAND_GUI1ASas:
                if(onGUI1ASas()) {
                    ev.consume();
                    return;
                }
                break;

        }
        if(ev.getComponent() != null) {
            handleComponentAction(ev.getComponent(), ev);
        }
    }

    protected void exitForm(Form f) {
        if("GUI 1".equals(f.getName())) {
            exitGUI1(f);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void exitGUI1(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("GUI 1".equals(f.getName())) {
            beforeGUI1(f);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void beforeGUI1(Form f) {
    }

    protected void beforeShowContainer(Container c) {
    aboutToShowThisContainer = c;
        if("GUI 1".equals(c.getName())) {
            beforeContainerGUI1(c);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void beforeContainerGUI1(Container c) {
    }

    protected void postShow(Form f) {
        if("GUI 1".equals(f.getName())) {
            postGUI1(f);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void postGUI1(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("GUI 1".equals(c.getName())) {
            postContainerGUI1(c);
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void postContainerGUI1(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("GUI 1".equals(rootName)) {
            onCreateGUI1();
            aboutToShowThisContainer = null;
            return;
        }

    }


    protected void onCreateGUI1() {
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        if(c.getParent().getLeadParent() != null) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("GUI 1")) {
            if("Button".equals(c.getName())) {
                onGUI1_ButtonAction(c, event);
                return;
            }
        }
    }

      protected void onGUI1_ButtonAction(Component c, ActionEvent event) {
      }

}
