	.text
	.file	"program.c"
	.file	1 "/home/over/build/abra_lang/ex_handle/global_ex_table" "program.c"
	.globl	unused$212              # -- Begin function unused$212
	.p2align	4, 0x90
	.type	unused$212,@function
unused$212:                             # @"unused$212"
.Lfunc_begin0:
	.loc	1 7 0                   # program.c:7:0
	.cfi_startproc
# %bb.0:
	.loc	1 8 1 prologue_end      # program.c:8:1
	retq
.Ltmp0:
.Lfunc_end0:
	.size	unused$212, .Lfunc_end0-unused$212
	.cfi_endproc
                                        # -- End function
	.globl	bar                     # -- Begin function bar
	.p2align	4, 0x90
	.type	bar,@function
bar:                                    # @bar
.Lfunc_begin1:
	.loc	1 10 0                  # program.c:10:0
	.cfi_startproc
# %bb.0:
	pushq	%rax
	.cfi_def_cfa_offset 16
	#DEBUG_VALUE: bar:x <- $edi
.Ltmp1:
	.loc	1 11 8 prologue_end     # program.c:11:8
	movl	%edi, 4(%rsp)           # 4-byte Spill
.Ltmp2:
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 4] [$rsp+0]
	callq	rand
	#APP
.Leh_label1:
	#NO_APP
	#APP
	movw	%ax, .Leh_label1-bar
	#NO_APP
	#APP
	.short	.Leh_label1-bar
	#NO_APP
	movl	%eax, (%rsp)            # 4-byte Spill
.Ltmp3:
.Ltmp4:                                 # Block address taken
# %bb.1:                                # %eh_branch1
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 4] [$rsp+0]
	.loc	1 11 19 is_stmt 0       # program.c:11:19
	movl	(%rsp), %eax            # 4-byte Reload
	andl	$1, %eax
	cmpl	$0, %eax
.Ltmp5:
	.loc	1 11 8                  # program.c:11:8
	jne	.LBB1_4
.Ltmp6:
# %bb.2:
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 4] [$rsp+0]
	.loc	1 12 2 is_stmt 1        # program.c:12:2
	movabsq	$.L.str, %rdi
	callq	raise
	#APP
.Leh_label2:
	#NO_APP
	#APP
	movw	%ax, .Leh_label2-bar
	#NO_APP
	#APP
	.short	.Leh_label2-bar
	#NO_APP
.Ltmp7:
# %bb.3:                                # %eh_branch2
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 4] [$rsp+0]
	jmp	.LBB1_4
.Ltmp8:
.LBB1_4:
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 4] [$rsp+0]
	.loc	1 14 14                 # program.c:14:14
	movl	4(%rsp), %eax           # 4-byte Reload
	imull	%eax, %eax
	.loc	1 14 5 is_stmt 0        # program.c:14:5
	popq	%rcx
.Ltmp9:
	.cfi_def_cfa_offset 8
	retq
.Ltmp10:
.Lfunc_end1:
	.size	bar, .Lfunc_end1-bar
	.cfi_endproc
                                        # -- End function
	.globl	baz                     # -- Begin function baz
	.p2align	4, 0x90
	.type	baz,@function
baz:                                    # @baz
.Lfunc_begin2:
	.loc	1 17 0 is_stmt 1        # program.c:17:0
	.cfi_startproc
# %bb.0:
	subq	$24, %rsp
	.cfi_def_cfa_offset 32
	#DEBUG_VALUE: baz:x <- $edi
	#DEBUG_VALUE: baz:y <- $esi
.Ltmp11:
	#DEBUG_VALUE: bar:x <- $edi
	.loc	1 11 8 prologue_end     # program.c:11:8
	movl	%edi, 20(%rsp)          # 4-byte Spill
.Ltmp12:
	#DEBUG_VALUE: baz:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	movl	%esi, 16(%rsp)          # 4-byte Spill
.Ltmp13:
	#DEBUG_VALUE: baz:y <- [DW_OP_plus_uconst 16] [$rsp+0]
	callq	rand
	#APP
.Leh_label3:
	#NO_APP
	#APP
	movw	%ax, .Leh_label3-baz
	#NO_APP
	#APP
	.short	.Leh_label3-baz
	#NO_APP
	movl	%eax, 12(%rsp)          # 4-byte Spill
.Ltmp14:
.Ltmp15:                                # Block address taken
# %bb.1:                                # %eh_branch3
	#DEBUG_VALUE: baz:y <- [DW_OP_plus_uconst 16] [$rsp+0]
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	#DEBUG_VALUE: baz:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	.loc	1 11 19 is_stmt 0       # program.c:11:19
	movl	12(%rsp), %eax          # 4-byte Reload
	andl	$1, %eax
	cmpl	$0, %eax
.Ltmp16:
	.loc	1 11 8                  # program.c:11:8
	jne	.LBB2_4
.Ltmp17:
# %bb.2:
	#DEBUG_VALUE: baz:y <- [DW_OP_plus_uconst 16] [$rsp+0]
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	#DEBUG_VALUE: baz:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	.loc	1 12 2 is_stmt 1        # program.c:12:2
	movabsq	$.L.str, %rdi
	callq	raise
	#APP
.Leh_label4:
	#NO_APP
	#APP
	movw	%ax, .Leh_label4-baz
	#NO_APP
	#APP
	.short	.Leh_label4-baz
	#NO_APP
.Ltmp18:
# %bb.3:                                # %eh_branch4
	#DEBUG_VALUE: baz:y <- [DW_OP_plus_uconst 16] [$rsp+0]
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	#DEBUG_VALUE: baz:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	jmp	.LBB2_4
.Ltmp19:
.LBB2_4:
	#DEBUG_VALUE: baz:y <- [DW_OP_plus_uconst 16] [$rsp+0]
	#DEBUG_VALUE: bar:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	#DEBUG_VALUE: baz:x <- [DW_OP_plus_uconst 20] [$rsp+0]
	.loc	1 14 14                 # program.c:14:14
	movl	20(%rsp), %eax          # 4-byte Reload
	imull	%eax, %eax
.Ltmp20:
	.loc	1 18 19                 # program.c:18:19
	movl	16(%rsp), %ecx          # 4-byte Reload
	addl	%ecx, %eax
	.loc	1 18 5 is_stmt 0        # program.c:18:5
	addq	$24, %rsp
.Ltmp21:
	.cfi_def_cfa_offset 8
	retq
.Ltmp22:
.Lfunc_end2:
	.size	baz, .Lfunc_end2-baz
	.cfi_endproc
                                        # -- End function
	.globl	_main                   # -- Begin function _main
	.p2align	4, 0x90
	.type	_main,@function
_main:                                  # @_main
.Lfunc_begin3:
	.loc	1 21 0 is_stmt 1        # program.c:21:0
	.cfi_startproc
# %bb.0:
	pushq	%rax
	.cfi_def_cfa_offset 16
.Ltmp23:
	#DEBUG_VALUE: baz:x <- 2
	#DEBUG_VALUE: baz:y <- 2
	#DEBUG_VALUE: bar:x <- 2
	.loc	1 11 8 prologue_end     # program.c:11:8
	callq	rand
	#APP
.Leh_label5:
	#NO_APP
	#APP
	movw	%ax, .Leh_label5-_main
	#NO_APP
	#APP
	.short	.Leh_label5-_main
	#NO_APP
	movl	%eax, 4(%rsp)           # 4-byte Spill
.Ltmp24:                                # Block address taken
# %bb.1:                                # %eh_branch5
	.loc	1 11 19 is_stmt 0       # program.c:11:19
	movl	4(%rsp), %eax           # 4-byte Reload
	andl	$1, %eax
	cmpl	$0, %eax
.Ltmp25:
	.loc	1 11 8                  # program.c:11:8
	jne	.LBB3_4
# %bb.2:
.Ltmp26:
	.loc	1 12 2 is_stmt 1        # program.c:12:2
	movabsq	$.L.str, %rdi
	callq	raise
	#APP
.Leh_label6:
	#NO_APP
	#APP
	movw	%ax, .Leh_label6-_main
	#NO_APP
	#APP
	.short	.Leh_label6-_main
	#NO_APP
# %bb.3:                                # %eh_branch6
	jmp	.LBB3_4
.Ltmp27:
.LBB3_4:
	.loc	1 0 2 is_stmt 0         # program.c:0:2
	xorl	%eax, %eax
	.loc	1 23 5 is_stmt 1        # program.c:23:5
	popq	%rcx
	.cfi_def_cfa_offset 8
	retq
.Ltmp28:
.Lfunc_end3:
	.size	_main, .Lfunc_end3-_main
	.cfi_endproc
                                        # -- End function
	.globl	main                    # -- Begin function main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
.Lfunc_begin4:
	.loc	1 25 0                  # program.c:25:0
	.cfi_startproc
# %bb.0:
	pushq	%rax
	.cfi_def_cfa_offset 16
.Ltmp29:
	#DEBUG_VALUE: baz:x <- 2
	#DEBUG_VALUE: baz:y <- 2
	#DEBUG_VALUE: bar:x <- 2
	.loc	1 11 8 prologue_end     # program.c:11:8
	callq	rand
	#APP
.Leh_label7:
	#NO_APP
	#APP
	movw	%ax, .Leh_label7-main
	#NO_APP
	#APP
	.short	.Leh_label7-main
	#NO_APP
	movl	%eax, 4(%rsp)           # 4-byte Spill
.Ltmp30:                                # Block address taken
# %bb.1:                                # %eh_branch7
	.loc	1 11 19 is_stmt 0       # program.c:11:19
	movl	4(%rsp), %eax           # 4-byte Reload
	andl	$1, %eax
	cmpl	$0, %eax
.Ltmp31:
	.loc	1 11 8                  # program.c:11:8
	jne	.LBB4_4
# %bb.2:
.Ltmp32:
	.loc	1 12 2 is_stmt 1        # program.c:12:2
	movabsq	$.L.str, %rdi
	callq	raise
	#APP
.Leh_label8:
	#NO_APP
	#APP
	movw	%ax, .Leh_label8-main
	#NO_APP
	#APP
	.short	.Leh_label8-main
	#NO_APP
# %bb.3:                                # %eh_branch8
	jmp	.LBB4_4
.Ltmp33:
.LBB4_4:
	.loc	1 0 2 is_stmt 0         # program.c:0:2
	xorl	%eax, %eax
	.loc	1 26 5 is_stmt 1        # program.c:26:5
	popq	%rcx
	.cfi_def_cfa_offset 8
	retq
.Ltmp34:
.Lfunc_end4:
	.size	main, .Lfunc_end4-main
	.cfi_endproc
                                        # -- End function
	.type	tbl.bar,@object         # @tbl.bar
	.section	.rodata,"a",@progbits
	.globl	tbl.bar
	.p2align	3
tbl.bar:
	.short	.Ltmp4-bar
	.short	0                       # 0x0
	.size	tbl.bar, 4

	.type	tbl.baz,@object         # @tbl.baz
	.globl	tbl.baz
	.p2align	3
tbl.baz:
	.short	.Ltmp15-baz
	.short	0                       # 0x0
	.size	tbl.baz, 4

	.type	tbl._main,@object       # @tbl._main
	.globl	tbl._main
	.p2align	3
tbl._main:
	.short	.Ltmp24-_main
	.short	0                       # 0x0
	.size	tbl._main, 4

	.type	tbl.main,@object        # @tbl.main
	.globl	tbl.main
	.p2align	3
tbl.main:
	.short	.Ltmp30-main
	.short	0                       # 0x0
	.size	tbl.main, 4

	.type	XX_MARKER_XX,@object    # @XX_MARKER_XX
	.data
	.globl	XX_MARKER_XX
	.p2align	2
XX_MARKER_XX:
	.long	9000                    # 0x2328
	.size	XX_MARKER_XX, 4

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"an error has occured"
	.size	.L.str, 21

	.section	.debug_str,"MS",@progbits,1
.Linfo_string0:
	.asciz	"clang version 7.0.1-+rc3-2 (tags/RELEASE_701/rc3)" # string offset=0
.Linfo_string1:
	.asciz	"program.c"             # string offset=50
.Linfo_string2:
	.asciz	"/home/over/build/abra_lang/ex_handle/global_ex_table" # string offset=60
.Linfo_string3:
	.asciz	"XX_MARKER_XX"          # string offset=113
.Linfo_string4:
	.asciz	"int"                   # string offset=126
.Linfo_string5:
	.asciz	"bar"                   # string offset=130
.Linfo_string6:
	.asciz	"x"                     # string offset=134
.Linfo_string7:
	.asciz	"baz"                   # string offset=136
.Linfo_string8:
	.asciz	"y"                     # string offset=140
.Linfo_string9:
	.asciz	"_main"                 # string offset=142
.Linfo_string10:
	.asciz	"unused$212"            # string offset=148
.Linfo_string11:
	.asciz	"main"                  # string offset=159
	.section	.debug_loc,"",@progbits
.Ldebug_loc0:
	.quad	.Lfunc_begin1-.Lfunc_begin0
	.quad	.Ltmp2-.Lfunc_begin0
	.short	1                       # Loc expr size
	.byte	85                      # super-register DW_OP_reg5
	.quad	.Ltmp2-.Lfunc_begin0
	.quad	.Ltmp9-.Lfunc_begin0
	.short	2                       # Loc expr size
	.byte	119                     # DW_OP_breg7
	.byte	4                       # 4
	.quad	0
	.quad	0
.Ldebug_loc1:
	.quad	.Lfunc_begin2-.Lfunc_begin0
	.quad	.Ltmp12-.Lfunc_begin0
	.short	1                       # Loc expr size
	.byte	85                      # super-register DW_OP_reg5
	.quad	.Ltmp12-.Lfunc_begin0
	.quad	.Ltmp21-.Lfunc_begin0
	.short	2                       # Loc expr size
	.byte	119                     # DW_OP_breg7
	.byte	20                      # 20
	.quad	0
	.quad	0
.Ldebug_loc2:
	.quad	.Lfunc_begin2-.Lfunc_begin0
	.quad	.Ltmp13-.Lfunc_begin0
	.short	1                       # Loc expr size
	.byte	84                      # super-register DW_OP_reg4
	.quad	.Ltmp13-.Lfunc_begin0
	.quad	.Ltmp21-.Lfunc_begin0
	.short	2                       # Loc expr size
	.byte	119                     # DW_OP_breg7
	.byte	16                      # 16
	.quad	0
	.quad	0
.Ldebug_loc3:
	.quad	.Ltmp11-.Lfunc_begin0
	.quad	.Ltmp12-.Lfunc_begin0
	.short	1                       # Loc expr size
	.byte	85                      # super-register DW_OP_reg5
	.quad	.Ltmp12-.Lfunc_begin0
	.quad	.Ltmp21-.Lfunc_begin0
	.short	2                       # Loc expr size
	.byte	119                     # DW_OP_breg7
	.byte	20                      # 20
	.quad	0
	.quad	0
	.section	.debug_abbrev,"",@progbits
	.byte	1                       # Abbreviation Code
	.byte	17                      # DW_TAG_compile_unit
	.byte	1                       # DW_CHILDREN_yes
	.byte	37                      # DW_AT_producer
	.byte	14                      # DW_FORM_strp
	.byte	19                      # DW_AT_language
	.byte	5                       # DW_FORM_data2
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	16                      # DW_AT_stmt_list
	.byte	23                      # DW_FORM_sec_offset
	.byte	27                      # DW_AT_comp_dir
	.byte	14                      # DW_FORM_strp
	.ascii	"\264B"                 # DW_AT_GNU_pubnames
	.byte	25                      # DW_FORM_flag_present
	.byte	17                      # DW_AT_low_pc
	.byte	1                       # DW_FORM_addr
	.byte	18                      # DW_AT_high_pc
	.byte	6                       # DW_FORM_data4
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	2                       # Abbreviation Code
	.byte	52                      # DW_TAG_variable
	.byte	0                       # DW_CHILDREN_no
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	73                      # DW_AT_type
	.byte	19                      # DW_FORM_ref4
	.byte	63                      # DW_AT_external
	.byte	25                      # DW_FORM_flag_present
	.byte	58                      # DW_AT_decl_file
	.byte	11                      # DW_FORM_data1
	.byte	59                      # DW_AT_decl_line
	.byte	11                      # DW_FORM_data1
	.byte	2                       # DW_AT_location
	.byte	24                      # DW_FORM_exprloc
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	3                       # Abbreviation Code
	.byte	36                      # DW_TAG_base_type
	.byte	0                       # DW_CHILDREN_no
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	62                      # DW_AT_encoding
	.byte	11                      # DW_FORM_data1
	.byte	11                      # DW_AT_byte_size
	.byte	11                      # DW_FORM_data1
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	4                       # Abbreviation Code
	.byte	46                      # DW_TAG_subprogram
	.byte	0                       # DW_CHILDREN_no
	.byte	17                      # DW_AT_low_pc
	.byte	1                       # DW_FORM_addr
	.byte	18                      # DW_AT_high_pc
	.byte	6                       # DW_FORM_data4
	.byte	64                      # DW_AT_frame_base
	.byte	24                      # DW_FORM_exprloc
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	58                      # DW_AT_decl_file
	.byte	11                      # DW_FORM_data1
	.byte	59                      # DW_AT_decl_line
	.byte	11                      # DW_FORM_data1
	.byte	63                      # DW_AT_external
	.byte	25                      # DW_FORM_flag_present
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	5                       # Abbreviation Code
	.byte	46                      # DW_TAG_subprogram
	.byte	1                       # DW_CHILDREN_yes
	.byte	17                      # DW_AT_low_pc
	.byte	1                       # DW_FORM_addr
	.byte	18                      # DW_AT_high_pc
	.byte	6                       # DW_FORM_data4
	.byte	64                      # DW_AT_frame_base
	.byte	24                      # DW_FORM_exprloc
	.byte	49                      # DW_AT_abstract_origin
	.byte	19                      # DW_FORM_ref4
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	6                       # Abbreviation Code
	.byte	5                       # DW_TAG_formal_parameter
	.byte	0                       # DW_CHILDREN_no
	.byte	2                       # DW_AT_location
	.byte	23                      # DW_FORM_sec_offset
	.byte	49                      # DW_AT_abstract_origin
	.byte	19                      # DW_FORM_ref4
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	7                       # Abbreviation Code
	.byte	46                      # DW_TAG_subprogram
	.byte	1                       # DW_CHILDREN_yes
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	58                      # DW_AT_decl_file
	.byte	11                      # DW_FORM_data1
	.byte	59                      # DW_AT_decl_line
	.byte	11                      # DW_FORM_data1
	.byte	39                      # DW_AT_prototyped
	.byte	25                      # DW_FORM_flag_present
	.byte	73                      # DW_AT_type
	.byte	19                      # DW_FORM_ref4
	.byte	63                      # DW_AT_external
	.byte	25                      # DW_FORM_flag_present
	.byte	32                      # DW_AT_inline
	.byte	11                      # DW_FORM_data1
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	8                       # Abbreviation Code
	.byte	5                       # DW_TAG_formal_parameter
	.byte	0                       # DW_CHILDREN_no
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	58                      # DW_AT_decl_file
	.byte	11                      # DW_FORM_data1
	.byte	59                      # DW_AT_decl_line
	.byte	11                      # DW_FORM_data1
	.byte	73                      # DW_AT_type
	.byte	19                      # DW_FORM_ref4
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	9                       # Abbreviation Code
	.byte	29                      # DW_TAG_inlined_subroutine
	.byte	1                       # DW_CHILDREN_yes
	.byte	49                      # DW_AT_abstract_origin
	.byte	19                      # DW_FORM_ref4
	.byte	17                      # DW_AT_low_pc
	.byte	1                       # DW_FORM_addr
	.byte	18                      # DW_AT_high_pc
	.byte	6                       # DW_FORM_data4
	.byte	88                      # DW_AT_call_file
	.byte	11                      # DW_FORM_data1
	.byte	89                      # DW_AT_call_line
	.byte	11                      # DW_FORM_data1
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	10                      # Abbreviation Code
	.byte	5                       # DW_TAG_formal_parameter
	.byte	0                       # DW_CHILDREN_no
	.byte	28                      # DW_AT_const_value
	.byte	13                      # DW_FORM_sdata
	.byte	49                      # DW_AT_abstract_origin
	.byte	19                      # DW_FORM_ref4
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	11                      # Abbreviation Code
	.byte	46                      # DW_TAG_subprogram
	.byte	0                       # DW_CHILDREN_no
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	58                      # DW_AT_decl_file
	.byte	11                      # DW_FORM_data1
	.byte	59                      # DW_AT_decl_line
	.byte	11                      # DW_FORM_data1
	.byte	73                      # DW_AT_type
	.byte	19                      # DW_FORM_ref4
	.byte	63                      # DW_AT_external
	.byte	25                      # DW_FORM_flag_present
	.byte	32                      # DW_AT_inline
	.byte	11                      # DW_FORM_data1
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	12                      # Abbreviation Code
	.byte	46                      # DW_TAG_subprogram
	.byte	1                       # DW_CHILDREN_yes
	.byte	17                      # DW_AT_low_pc
	.byte	1                       # DW_FORM_addr
	.byte	18                      # DW_AT_high_pc
	.byte	6                       # DW_FORM_data4
	.byte	64                      # DW_AT_frame_base
	.byte	24                      # DW_FORM_exprloc
	.byte	3                       # DW_AT_name
	.byte	14                      # DW_FORM_strp
	.byte	58                      # DW_AT_decl_file
	.byte	11                      # DW_FORM_data1
	.byte	59                      # DW_AT_decl_line
	.byte	11                      # DW_FORM_data1
	.byte	73                      # DW_AT_type
	.byte	19                      # DW_FORM_ref4
	.byte	63                      # DW_AT_external
	.byte	25                      # DW_FORM_flag_present
	.byte	0                       # EOM(1)
	.byte	0                       # EOM(2)
	.byte	0                       # EOM(3)
	.section	.debug_info,"",@progbits
.Lcu_begin0:
	.long	437                     # Length of Unit
	.short	4                       # DWARF version number
	.long	.debug_abbrev           # Offset Into Abbrev. Section
	.byte	8                       # Address Size (in bytes)
	.byte	1                       # Abbrev [1] 0xb:0x1ae DW_TAG_compile_unit
	.long	.Linfo_string0          # DW_AT_producer
	.short	12                      # DW_AT_language
	.long	.Linfo_string1          # DW_AT_name
	.long	.Lline_table_start0     # DW_AT_stmt_list
	.long	.Linfo_string2          # DW_AT_comp_dir
                                        # DW_AT_GNU_pubnames
	.quad	.Lfunc_begin0           # DW_AT_low_pc
	.long	.Lfunc_end4-.Lfunc_begin0 # DW_AT_high_pc
	.byte	2                       # Abbrev [2] 0x2a:0x15 DW_TAG_variable
	.long	.Linfo_string3          # DW_AT_name
	.long	63                      # DW_AT_type
                                        # DW_AT_external
	.byte	1                       # DW_AT_decl_file
	.byte	3                       # DW_AT_decl_line
	.byte	9                       # DW_AT_location
	.byte	3
	.quad	XX_MARKER_XX
	.byte	3                       # Abbrev [3] 0x3f:0x7 DW_TAG_base_type
	.long	.Linfo_string4          # DW_AT_name
	.byte	5                       # DW_AT_encoding
	.byte	4                       # DW_AT_byte_size
	.byte	4                       # Abbrev [4] 0x46:0x15 DW_TAG_subprogram
	.quad	.Lfunc_begin0           # DW_AT_low_pc
	.long	.Lfunc_end0-.Lfunc_begin0 # DW_AT_high_pc
	.byte	1                       # DW_AT_frame_base
	.byte	87
	.long	.Linfo_string10         # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	7                       # DW_AT_decl_line
                                        # DW_AT_external
	.byte	5                       # Abbrev [5] 0x5b:0x1d DW_TAG_subprogram
	.quad	.Lfunc_begin1           # DW_AT_low_pc
	.long	.Lfunc_end1-.Lfunc_begin1 # DW_AT_high_pc
	.byte	1                       # DW_AT_frame_base
	.byte	87
	.long	120                     # DW_AT_abstract_origin
	.byte	6                       # Abbrev [6] 0x6e:0x9 DW_TAG_formal_parameter
	.long	.Ldebug_loc0            # DW_AT_location
	.long	132                     # DW_AT_abstract_origin
	.byte	0                       # End Of Children Mark
	.byte	7                       # Abbrev [7] 0x78:0x18 DW_TAG_subprogram
	.long	.Linfo_string5          # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	10                      # DW_AT_decl_line
                                        # DW_AT_prototyped
	.long	63                      # DW_AT_type
                                        # DW_AT_external
	.byte	1                       # DW_AT_inline
	.byte	8                       # Abbrev [8] 0x84:0xb DW_TAG_formal_parameter
	.long	.Linfo_string6          # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	10                      # DW_AT_decl_line
	.long	63                      # DW_AT_type
	.byte	0                       # End Of Children Mark
	.byte	5                       # Abbrev [5] 0x90:0x43 DW_TAG_subprogram
	.quad	.Lfunc_begin2           # DW_AT_low_pc
	.long	.Lfunc_end2-.Lfunc_begin2 # DW_AT_high_pc
	.byte	1                       # DW_AT_frame_base
	.byte	87
	.long	211                     # DW_AT_abstract_origin
	.byte	6                       # Abbrev [6] 0xa3:0x9 DW_TAG_formal_parameter
	.long	.Ldebug_loc1            # DW_AT_location
	.long	223                     # DW_AT_abstract_origin
	.byte	6                       # Abbrev [6] 0xac:0x9 DW_TAG_formal_parameter
	.long	.Ldebug_loc2            # DW_AT_location
	.long	234                     # DW_AT_abstract_origin
	.byte	9                       # Abbrev [9] 0xb5:0x1d DW_TAG_inlined_subroutine
	.long	120                     # DW_AT_abstract_origin
	.quad	.Ltmp11                 # DW_AT_low_pc
	.long	.Ltmp20-.Ltmp11         # DW_AT_high_pc
	.byte	1                       # DW_AT_call_file
	.byte	18                      # DW_AT_call_line
	.byte	6                       # Abbrev [6] 0xc8:0x9 DW_TAG_formal_parameter
	.long	.Ldebug_loc3            # DW_AT_location
	.long	132                     # DW_AT_abstract_origin
	.byte	0                       # End Of Children Mark
	.byte	0                       # End Of Children Mark
	.byte	7                       # Abbrev [7] 0xd3:0x23 DW_TAG_subprogram
	.long	.Linfo_string7          # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	17                      # DW_AT_decl_line
                                        # DW_AT_prototyped
	.long	63                      # DW_AT_type
                                        # DW_AT_external
	.byte	1                       # DW_AT_inline
	.byte	8                       # Abbrev [8] 0xdf:0xb DW_TAG_formal_parameter
	.long	.Linfo_string6          # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	17                      # DW_AT_decl_line
	.long	63                      # DW_AT_type
	.byte	8                       # Abbrev [8] 0xea:0xb DW_TAG_formal_parameter
	.long	.Linfo_string8          # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	17                      # DW_AT_decl_line
	.long	63                      # DW_AT_type
	.byte	0                       # End Of Children Mark
	.byte	5                       # Abbrev [5] 0xf6:0x4e DW_TAG_subprogram
	.quad	.Lfunc_begin3           # DW_AT_low_pc
	.long	.Lfunc_end3-.Lfunc_begin3 # DW_AT_high_pc
	.byte	1                       # DW_AT_frame_base
	.byte	87
	.long	324                     # DW_AT_abstract_origin
	.byte	9                       # Abbrev [9] 0x109:0x3a DW_TAG_inlined_subroutine
	.long	211                     # DW_AT_abstract_origin
	.quad	.Ltmp23                 # DW_AT_low_pc
	.long	.Ltmp27-.Ltmp23         # DW_AT_high_pc
	.byte	1                       # DW_AT_call_file
	.byte	22                      # DW_AT_call_line
	.byte	10                      # Abbrev [10] 0x11c:0x6 DW_TAG_formal_parameter
	.byte	2                       # DW_AT_const_value
	.long	223                     # DW_AT_abstract_origin
	.byte	10                      # Abbrev [10] 0x122:0x6 DW_TAG_formal_parameter
	.byte	2                       # DW_AT_const_value
	.long	234                     # DW_AT_abstract_origin
	.byte	9                       # Abbrev [9] 0x128:0x1a DW_TAG_inlined_subroutine
	.long	120                     # DW_AT_abstract_origin
	.quad	.Ltmp23                 # DW_AT_low_pc
	.long	.Ltmp27-.Ltmp23         # DW_AT_high_pc
	.byte	1                       # DW_AT_call_file
	.byte	18                      # DW_AT_call_line
	.byte	10                      # Abbrev [10] 0x13b:0x6 DW_TAG_formal_parameter
	.byte	2                       # DW_AT_const_value
	.long	132                     # DW_AT_abstract_origin
	.byte	0                       # End Of Children Mark
	.byte	0                       # End Of Children Mark
	.byte	0                       # End Of Children Mark
	.byte	11                      # Abbrev [11] 0x144:0xc DW_TAG_subprogram
	.long	.Linfo_string9          # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	21                      # DW_AT_decl_line
	.long	63                      # DW_AT_type
                                        # DW_AT_external
	.byte	1                       # DW_AT_inline
	.byte	12                      # Abbrev [12] 0x150:0x68 DW_TAG_subprogram
	.quad	.Lfunc_begin4           # DW_AT_low_pc
	.long	.Lfunc_end4-.Lfunc_begin4 # DW_AT_high_pc
	.byte	1                       # DW_AT_frame_base
	.byte	87
	.long	.Linfo_string11         # DW_AT_name
	.byte	1                       # DW_AT_decl_file
	.byte	25                      # DW_AT_decl_line
	.long	63                      # DW_AT_type
                                        # DW_AT_external
	.byte	9                       # Abbrev [9] 0x169:0x4e DW_TAG_inlined_subroutine
	.long	324                     # DW_AT_abstract_origin
	.quad	.Ltmp29                 # DW_AT_low_pc
	.long	.Ltmp33-.Ltmp29         # DW_AT_high_pc
	.byte	1                       # DW_AT_call_file
	.byte	26                      # DW_AT_call_line
	.byte	9                       # Abbrev [9] 0x17c:0x3a DW_TAG_inlined_subroutine
	.long	211                     # DW_AT_abstract_origin
	.quad	.Ltmp29                 # DW_AT_low_pc
	.long	.Ltmp33-.Ltmp29         # DW_AT_high_pc
	.byte	1                       # DW_AT_call_file
	.byte	22                      # DW_AT_call_line
	.byte	10                      # Abbrev [10] 0x18f:0x6 DW_TAG_formal_parameter
	.byte	2                       # DW_AT_const_value
	.long	223                     # DW_AT_abstract_origin
	.byte	10                      # Abbrev [10] 0x195:0x6 DW_TAG_formal_parameter
	.byte	2                       # DW_AT_const_value
	.long	234                     # DW_AT_abstract_origin
	.byte	9                       # Abbrev [9] 0x19b:0x1a DW_TAG_inlined_subroutine
	.long	120                     # DW_AT_abstract_origin
	.quad	.Ltmp29                 # DW_AT_low_pc
	.long	.Ltmp33-.Ltmp29         # DW_AT_high_pc
	.byte	1                       # DW_AT_call_file
	.byte	18                      # DW_AT_call_line
	.byte	10                      # Abbrev [10] 0x1ae:0x6 DW_TAG_formal_parameter
	.byte	2                       # DW_AT_const_value
	.long	132                     # DW_AT_abstract_origin
	.byte	0                       # End Of Children Mark
	.byte	0                       # End Of Children Mark
	.byte	0                       # End Of Children Mark
	.byte	0                       # End Of Children Mark
	.byte	0                       # End Of Children Mark
	.section	.debug_macinfo,"",@progbits
	.byte	0                       # End Of Macro List Mark
	.section	.debug_pubnames,"",@progbits
	.long	.LpubNames_end0-.LpubNames_begin0 # Length of Public Names Info
.LpubNames_begin0:
	.short	2                       # DWARF Version
	.long	.Lcu_begin0             # Offset of Compilation Unit Info
	.long	441                     # Compilation Unit Length
	.long	42                      # DIE offset
	.asciz	"XX_MARKER_XX"          # External Name
	.long	324                     # DIE offset
	.asciz	"_main"                 # External Name
	.long	120                     # DIE offset
	.asciz	"bar"                   # External Name
	.long	336                     # DIE offset
	.asciz	"main"                  # External Name
	.long	211                     # DIE offset
	.asciz	"baz"                   # External Name
	.long	70                      # DIE offset
	.asciz	"unused$212"            # External Name
	.long	0                       # End Mark
.LpubNames_end0:
	.section	.debug_pubtypes,"",@progbits
	.long	.LpubTypes_end0-.LpubTypes_begin0 # Length of Public Types Info
.LpubTypes_begin0:
	.short	2                       # DWARF Version
	.long	.Lcu_begin0             # Offset of Compilation Unit Info
	.long	441                     # Compilation Unit Length
	.long	63                      # DIE offset
	.asciz	"int"                   # External Name
	.long	0                       # End Mark
.LpubTypes_end0:

	.ident	"clang version 7.0.1-+rc3-2 (tags/RELEASE_701/rc3)"
	.section	".note.GNU-stack","",@progbits
	.addrsig
	.addrsig_sym tbl.bar
	.addrsig_sym tbl.baz
	.addrsig_sym tbl._main
	.addrsig_sym tbl.main
	.section	.debug_line,"",@progbits
.Lline_table_start0:
