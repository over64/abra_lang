	.text
	.file	"main.orig.ll"
	.globl	bar
	.align	16, 0x90
	.type	bar,@function
	.short	.Ltmp0-bar              # @bar
	.short	0                       # 0x0
bar:
	.cfi_startproc
# BB#0:
	jmp	.LBB0_1
.Ltmp0:                                 # Block address taken
.LBB0_1:
	retq
.Lfunc_end0:
	.size	bar, .Lfunc_end0-bar
	.cfi_endproc

	.globl	main
	.align	16, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# BB#0:
	pushq	%rbp
.Ltmp1:
	.cfi_def_cfa_offset 16
.Ltmp2:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
.Ltmp3:
	.cfi_def_cfa_register %rbp
	xorl	%eax, %eax
	movl	$0, -4(%rbp)
	popq	%rbp
	retq
.Lfunc_end1:
	.size	main, .Lfunc_end1-main
	.cfi_endproc


	.ident	"clang version 3.8.0-2 (tags/RELEASE_380/final)"
	.section	".note.GNU-stack","",@progbits
