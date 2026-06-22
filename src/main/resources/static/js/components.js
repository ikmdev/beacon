document.addEventListener('alpine:init', () => {
    console.log("Alpine is initializing components...");

    Alpine.data('jsonEditor', () => ({
        editor: null,
        init() {
            console.log("jsonEditor component is initializing.");
            // Ensure CodeMirror is available
            if (typeof CodeMirror === 'undefined') {
                console.error('CodeMirror is not loaded!');
                return;
            }

            this.editor = CodeMirror(this.$refs.editorContainer, {
                value: this.$refs.textarea.value,
                lineNumbers: true,
                mode: "application/json",
                theme: "dracula",
                matchBrackets: true,
                autoCloseBrackets: true,
                indentUnit: 2,
                tabSize: 2
            });

            this.editor.on('change', () => {
                this.$refs.textarea.value = this.editor.getValue();
            });

            this.editor.setSize("100%", "100%");
            console.log("CodeMirror editor created.");
        }
    }));
});